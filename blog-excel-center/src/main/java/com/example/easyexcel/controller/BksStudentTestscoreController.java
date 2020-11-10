package com.example.easyexcel.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 高中学生成绩表 前端控制器
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
@RestController
@RequestMapping("/student")
public class BksStudentTestscoreController {

    @Value("${words}")
    private String words;


    @GetMapping("/get")
    public String get(@RequestParam String name){
        return "hello," + name + "," + this.words;
    }

    @GetMapping("/exception")
    public void exception() throws Exception{
        throw new Exception("系统异常");
    }
}

