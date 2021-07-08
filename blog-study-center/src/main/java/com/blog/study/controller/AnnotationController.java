package com.blog.study.controller;

import com.blog.study.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class AnnotationController {


    @PreAuthorize("hasAuthority('p1')")
    @GetMapping(value = "/r1")
    public String r1(){
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源1";
    }

    @PreAuthorize("hasAuthority('p2')")
    @GetMapping(value = "/r2")
    public String r2(){
        //通过Spring Security API获取当前登录用户
        UserDTO user = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername() + "访问资源2";
    }

}
