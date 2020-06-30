package com.example.easyexcel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ZhenHX
 * @description swagger2配置类
 * @date 2020-05-27 16:46
 */
@Configuration
@EnableSwagger2
public class Swagger2Configration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 配置扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.easyexcel"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot添加Swagger2组件")
                .description("swagger2后台接口文档")
                .version("1.0")
                .build();
    }
}
