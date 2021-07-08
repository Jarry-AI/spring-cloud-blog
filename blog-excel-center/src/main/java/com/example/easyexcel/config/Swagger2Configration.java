package com.example.easyexcel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ZhenHX
 * @description swagger2配置类
 * @date 2020-05-27 16:46
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Configration {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("各种java工具测试模块")
                        .termsOfServiceUrl("http://localhost:8098/")
                        .contact("xx@163.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X easyexcel 版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.easyexcel.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
