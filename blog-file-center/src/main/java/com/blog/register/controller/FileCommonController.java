package com.blog.register.controller;

import com.blog.register.utils.AliyunOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * <p>
 * OSS文件各功能测试
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-07-02
 */
@RestController
@RequestMapping("/fileCommon")
public class FileCommonController {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Autowired
    AliyunOssUtil aliyunOssUtil;

    @GetMapping("/get")
    public String get(@RequestParam String name){
        return "my name is" + name + this.endpoint;
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            /*
             * 修改文件名称并保存文件
             */
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + suffix;
            /*
             * 入阿里云
             */
            aliyunOssUtil.upload(file.getInputStream(),fileName);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }



}
