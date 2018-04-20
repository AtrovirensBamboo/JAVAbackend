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

import javax.validation.Valid;
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
    public String processRegistration(@Valid Spitter spitter, Errors errors,Model model){

        logger.info("processRegistraction called");

        if (errors.hasErrors()){
            model.addAttribute("errors",Common.getErrorMessage(errors));
            return "RegisterForm";
        }else {
            spittleService.insertSpitter(spitter);

            return "redirect:/spitter/"+spitter.getUsername();
        }
    }
    //处理以用户名为路径参数的请求展示用户信息
    @RequestMapping(value = "/spitter/{username}")
    public String showSpitterProfile(@PathVariable String username,Model model){
        Spitter spitter;
        logger.info("showSpitterProfile called");
        spitter = spittleService.querySpitter(username);
        model.addAttribute("spitter",spitter);

        return "Profile";
    }
}
