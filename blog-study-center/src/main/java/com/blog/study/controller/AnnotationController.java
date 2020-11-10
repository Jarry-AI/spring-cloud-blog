package com.blog.study.controller;

import com.blog.study.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/test")
public class AnnotationController {


    /**
     * @param url
     * @author xiaofu
     * @description 获取当前链接重定向后的url
     * @date 2020/9/15 12:43
     */
    @GetMapping("/get")
    public String getLocation(String url) {

        return "";
    }



}
