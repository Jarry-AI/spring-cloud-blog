package com.example.easyexcel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easyexcel.entity.LikedCountDTO;
import com.example.easyexcel.enums.LikedStatusEnum;
import com.example.easyexcel.mapper.UserLikeMapper;
import com.example.easyexcel.model.UserLike;
import com.example.easyexcel.model.UserLikeNum;
import com.example.easyexcel.service.LikedService;
import com.example.easyexcel.service.RedisService;
import com.example.easyexcel.service.UserLikeNumService;
import com.example.easyexcel.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Service
public class LikedServiceImpl extends ServiceImpl<UserLikeMapper, UserLike> implements LikedService {
    @Autowired
    UserLikeService likeService;
    @Autowired
    RedisService redisService;
    @Autowired
    UserLikeNumService userService;
    /**
     * 保存或修改点赞记录
     * @param userLike
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserLike userLike) {
        return likeService.saveOrUpdate(userLike);
    }
    /**
     * 批量保存或修改
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAll(List<UserLike> list) {
        return likeService.saveBatch(list);
    }
    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @param pageable
     * @return
     */
    @Override
    public Page<UserLike> getLikedListByLikedUserId(String likedUserId, Page pageable) {

        return likeService.page(pageable,new LambdaQueryWrapper<UserLike>()
                .eq(UserLike::getLikedUserId,likedUserId).eq(UserLike::getStatus,LikedStatusEnum.LIKE.getCode()));
    }
    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @param pageable
     * @return
     */
    @Override
    public Page<UserLike> getLikedListByLikedPostId(String likedPostId, Page pageable) {
        return likeService.page(pageable,new LambdaQueryWrapper<UserLike>()
                .eq(UserLike::getLikedPostId,likedPostId).eq(UserLike::getStatus,LikedStatusEnum.LIKE.getCode()));
    }
    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return likeService.getOne(new LambdaQueryWrapper<UserLike>()
                .eq(UserLike::getLikedUserId,likedUserId)
                .eq(UserLike::getLikedPostId,likedPostId));
    }
    /**
     * 将Redis里的点赞数据存入数据库中
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike like : list) {
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }
    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            UserLikeNum user = userService.getById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (user != null){
                Integer likeNum = user.getLikeNum() + dto.getCount();
                user.setLikeNum(likeNum);
                //更新点赞数量
                userService.updateById(user);
            }
        }
    }
}
