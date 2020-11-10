package com.example.easyexcel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * <p>
 * 全局异常处理配置
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-07-15
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public HashMap<String,Object> badRequestException(Exception e){
        HashMap<String,Object> map = new HashMap();
        map.put("code", HttpStatus.BAD_REQUEST.value());
        map.put("message",e.getMessage());

        return map;
    }

}
