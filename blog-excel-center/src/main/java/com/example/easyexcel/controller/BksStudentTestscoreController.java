package com.example.easyexcel.controller;


import cn.hutool.extra.spring.SpringUtil;
import com.example.easyexcel.model.UserLikeNum;
import com.example.easyexcel.service.LikedService;
import com.example.easyexcel.service.UserLikeNumService;
import com.example.easyexcel.service.impl.RedisServiceImpl;
import com.example.easyexcel.service.impl.UserLikeNumServiceImpl;
import com.example.easyexcel.utils.RedisUtil;
import com.example.easyexcel.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 高中学生成绩表 前端控制器
 * </p>
 *
 * @author zhen huaxiang
 * @since 2020-06-06
 */
@RestController
@Slf4j
public class BksStudentTestscoreController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisServiceImpl redisService;

    @Resource
    private UserLikeNumService likeNumService;

    @GetMapping("/get")
    public String get(){
        log.info("===访问get===");

        try {
            UserLikeNumService bean = SpringUtils.getBean(UserLikeNumService.class);
            System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello,你好";
    }

    @GetMapping("/test")
    public String test(Integer userId,Integer likeNum){
        log.info("===访问test===");

        try {
            UserLikeNum userLikeNum = new UserLikeNum();
            userLikeNum.setLikeNum(likeNum);
            userLikeNum.setUserId(userId);
            UserLikeNumServiceImpl bean = SpringUtils.getBean(UserLikeNumServiceImpl.class);
            bean.save(userLikeNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test,你好";
    }


    @GetMapping("/add/like")
    public String addLike(@RequestParam String likedUserId, String likedPostId) throws Exception{
        redisService.saveLiked2Redis(likedUserId,likedPostId);
        return "success";
    }

    /**
     * 取消点赞
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @GetMapping("/remove/like")
    public String removeLike(@RequestParam String likedUserId, String likedPostId) {
        redisService.unlikeFromRedis(likedUserId,likedPostId);
        return "success";
    }

    public static void main(String[] args) {
         int i = 1;
        // 延时周期任务
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("hello: " + i);
            }
        },2,1, TimeUnit.SECONDS);

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("hello: ");
            }
        },2,TimeUnit.SECONDS);

    }
}

