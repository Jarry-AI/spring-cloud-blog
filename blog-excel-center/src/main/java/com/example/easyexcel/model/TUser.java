package com.example.easyexcel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-09
 */
@Data
public class TUser implements Serializable {

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private int id;

    private String username;

    private String password;

    private String fullName;

    private String mobile;
}
