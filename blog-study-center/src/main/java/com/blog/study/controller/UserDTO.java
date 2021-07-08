package com.blog.study.controller;

import lombok.Data;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2020-09-27
 */
@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}