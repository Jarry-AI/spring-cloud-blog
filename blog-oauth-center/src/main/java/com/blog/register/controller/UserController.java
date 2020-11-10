package com.blog.register.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户接口类（返回JSON）
 */
@RestController
public class UserController {

    /**
     * 获取登录后的Principal（需要登录）
     */
    @GetMapping("/getClass/{id}")
    public Object getClass(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "班级id:" + id;
    }

    /**
     * 获取登录后的UserDetails（需要登录）
     */
    @GetMapping("/getSchool/{id}")
    public Object getSchool(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "学校id:" + id;
    }

}
