package com.example.easyexcel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-09
 */
@Data
public class UserLikeNum {
    //主键id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer likeNum;
}
