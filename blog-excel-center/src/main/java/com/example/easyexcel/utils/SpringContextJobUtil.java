package com.example.easyexcel.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-18
 */
@Component
public class SpringContextJobUtil implements ApplicationContextAware  {

    private static ApplicationContext context;

    @Override
    @SuppressWarnings("static-access" )
    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {
        // TODO Auto-generated method stub
        this.context = contex;
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static String getMessage(String key){
        return context.getMessage(key, null, Locale.getDefault());
    }
}