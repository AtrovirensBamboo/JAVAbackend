package com.example.common;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

public class Common{
    public static String timeForm(Date date){
        String time = String.format("%tF",date);
        String form = String.format("%tT",date);
        return time+form;
    }
    public static HashMap<String,List> getErrorMessage(Errors errors){
        HashMap<String,List> messageMap = new HashMap<>();
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
}
