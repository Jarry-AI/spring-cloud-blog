package com.example.easyexcel.service;


import com.example.easyexcel.model.AuthUser;

/**
 * 用户服务类
 */
public interface AuthUserService {

    /**
     * 通过用户账号获取认证用户信息
     */
    AuthUser getAuthUserByUsername(String username);

}
