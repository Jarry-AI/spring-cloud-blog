package com.example.easyexcel.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.easyexcel.enums.LikedStatusEnum;
import lombok.Data;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Data
public class UserLike {
    //主键id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 被点赞的用户的id
    private String likedUserId;
    // 点赞的用户的id
    private String likedPostId;
    // 点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }
    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
