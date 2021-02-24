package com.example.easyexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SuppressWarnings(value = "all")
//@EnableGlobalMethodSecurity(prePostEnabled = true)

@SpringBootApplication
@MapperScan("com.example.easyexcel.mapper")
public class ExcelCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelCenterApplication.class, args);
    }

}
