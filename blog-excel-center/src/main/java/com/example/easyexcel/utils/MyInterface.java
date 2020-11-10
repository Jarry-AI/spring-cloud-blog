package com.example.easyexcel.utils;

import java.lang.annotation.*;

/**
 * @author Administrator
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface MyInterface {

    boolean isNotNull() default true;

}
