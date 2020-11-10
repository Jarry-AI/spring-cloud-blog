package com.blog.study.model;

import com.blog.study.annotation.MyAnnotation;
import lombok.Data;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-17
 */
@Data
public class User {

    @MyAnnotation
    private String userName;



}
