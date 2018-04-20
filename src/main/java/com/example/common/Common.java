package com.example.common;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.*;

//该类是project中所共同需要的方法
public class Common{
    //格式化时间格式为年-月-日 时：分：秒
    public static String timeForm(Date date){
        String time = String.format("%tF",date);
        String form = String.format("%tT",date);
        return time+form;
    }

    //表单校验，返回错误信息
    public static HashMap<String,ArrayList<String>> getErrorMessage(@NotNull Errors errors){
        HashMap<String,ArrayList<String>> messageMap = new HashMap<>();
        List<FieldError> errorObjects;
        String key;
        String value;

        errorObjects = errors.getFieldErrors();
        for (FieldError errorObject:errorObjects){
            key = errorObject.getField();
            value = errorObject.getDefaultMessage();

            if (messageMap.containsKey(key)) {
                messageMap.get(key).add(value);
            } else {
                messageMap.put(key,new ArrayList<String>());
                messageMap.get(key).add(value);
            }
        }
        return messageMap;
    }
    //存储上传文件
    public static String writeMultipartFile(MultipartFile multipartFile){
        //如果需要设置相对路径，可以@Autowired ServletContext，然后调用getRealpath()方法得到相对路径，
        //但是因为idea的编译输出路径和源码路径不一致，所以无法使用该方法
        String path = "E:/java/spring/springBeta/src/main/webapp/tmp/" + multipartFile.getOriginalFilename();
        File file = new File(path);
        //判断文件是否已存在，是则返回注册界面，否则将文件存入磁盘
        if (file.exists() || multipartFile.getSize() != 0) {
            return "RegisterForm";
        }else {
            try {
                multipartFile.transferTo(file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return null;
        }
    }
}
