package com.example.easyexcel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.example.easyexcel.mapper")
public class EasyexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyexcelApplication.class, args);
    }


}
