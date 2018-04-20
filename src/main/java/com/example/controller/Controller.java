package com.example.controller;

import com.example.common.Common;
import com.example.model.Spitter;
import com.example.model.Spittle;
import com.example.service.SpittleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private static final Log logger=LogFactory.getLog(Controller.class);
    private SpittleService spittleService;

    public SpittleService getSpittleService() {
        return spittleService;
    }
    @Autowired
    public void setSpittleService(SpittleService spittleService) {
        this.spittleService = spittleService;
    }

    //展示最近20条信息
    @RequestMapping(value = "/spittle-list",method =RequestMethod.GET)
    public String showInformation(Model model){
        List<Spittle> spittles;
        HashMap<Spittle,String> strings = new HashMap<>();

        logger.info("showInformation called");
        spittles = spittleService.query(20,20);
        for (Spittle spittle:spittles){
            strings.put(spittle,Common.timeForm(spittle.getTime()));
        }
        model.addAttribute("spittles",spittles);
        model.addAttribute("strings",strings);

        return "SpittleDetails";
    }
    //从首页转向注册界面
    @RequestMapping(value = "/register")
    public String showRegistractionForm(){
        logger.info("test called");

        return "RegisterForm";
    }
    //注册用户，保存至表demo.Spitter并重定向,启用表单内容校验，若不满足任意条件则返回注册页面
    @RequestMapping(value = "/processRegistraction")
    //@RequestPart 参数接受表单中的multiPartFile请求体
    public String processRegistration(@Valid Spitter spitter, Errors errors, Model model,
                                      @RequestPart MultipartFile picture){
        String pictureName = picture.getOriginalFilename();

        logger.info("processRegistraction called");
        //判断表单验证是否通过
        if (errors.hasErrors()){
            model.addAttribute("errors",Common.getErrorMessage(errors));
            return "RegisterForm";
        }else {
            String validatorString = Common.writeMultipartFile(picture);
            if (validatorString != null && !(validatorString.equals("RegisterForm"))) {
                //如果不指定图片访问端口，则访问请求会从其他端口访问服务器，可能会被防火墙拒绝请求
                spitter.setPicturePath("http://localhost:8080/tmp/" + pictureName);
                //判断数据库中username是否已存在
                if (spittleService.querySpitter(spitter.getUsername()) == null) {
                    spittleService.insertSpitter(spitter);

                    return "redirect:/spitter/" + spitter.getUsername();
                } else {
                    return "RegisterForm";
                }
            }else {
                return "RegisterForm";
            }
        }
    }
    //处理以用户名为路径参数的请求展示用户信息
    @RequestMapping(value = "/spitter/{username}")
    public String showSpitterProfile(@PathVariable String username, Model model, HttpSession session){
        Spitter spitter;
        logger.info("showSpitterProfile called");
        //从数据库查询是否已注册，如果已注册，则返回个人信息页面，否则返回注册页面
        spitter = spittleService.querySpitter(username);
        if (spitter != null) {
            model.addAttribute("spitter",spitter);
            //给session添加注册成功的属性
            session.setAttribute("login",Boolean.TRUE);

            return "Profile";
        } else {
            return "RegisterForm";
        }
    }
    @RequestMapping(value = "/download")
    public String downloadResources(HttpSession session, HttpServletResponse response){
        String directory = "E:/java/spring/springBeta/src/main/webapp/WEB-INF/downloadresource/head first java.pdf";
        Path path = Paths.get(directory);

        logger.info("downloadResources called");
        //判断session是否有注册成功的属性，是则开始下载文件，否则返回注册页面
        if (session.getAttribute("login") == Boolean.TRUE){
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition","attachment;filename=head first java.pdf");

            try {
                Files.copy(path,response.getOutputStream());
            } catch (IOException e) { }
            return null;
        }else {
            return "RegisterForm";
        }
    }
}
