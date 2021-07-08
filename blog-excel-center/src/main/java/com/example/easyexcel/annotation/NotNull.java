package com.example.easyexcel.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/**
 * 字段不能为空
 * @author Z
 */
public @interface NotNull {
    String message() default "不能为空!";
}
