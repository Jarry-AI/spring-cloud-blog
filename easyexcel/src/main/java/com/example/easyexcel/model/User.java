package com.example.easyexcel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ZhenHX
 * @description
 * @date 2020-04-03 16:07
 */
@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -5144055068797033748L;
    /**
     * 编号，自增
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 显示名称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    private Integer status = 0;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date createdTime;
    /**
     * 创建人用户名
     */
    private String createdBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date updatedTime;
    /**
     * 更新人用户名
     */
    private String updatedBy;




}
