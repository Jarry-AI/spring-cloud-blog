package com.example.easyexcel.enums;

import lombok.Getter;

/**
 * <p>
 * 用户点赞状态的枚举类
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Getter
public enum  LikedStatusEnum {

    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),;
    private Integer code;
    private String msg;
    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
