package com.example.easyexcel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 点赞统计
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Data
@AllArgsConstructor
public class LikedCountDTO implements Serializable {

    private String id;

    private Integer count;
}
