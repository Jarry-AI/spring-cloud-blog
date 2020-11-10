package com.blog.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }

}
