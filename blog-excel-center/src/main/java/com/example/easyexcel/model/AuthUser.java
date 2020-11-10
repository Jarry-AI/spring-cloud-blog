package com.example.easyexcel.model;

import java.util.List;

/**
 * 用户实体类
 */
public class AuthUser {
    /** 用户ID */
    private Long id;
    /** 用户账号 */
    private String username;
    /** 账号密码 */
    private String password;
    /** 角色集合 */
    private List<String> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
