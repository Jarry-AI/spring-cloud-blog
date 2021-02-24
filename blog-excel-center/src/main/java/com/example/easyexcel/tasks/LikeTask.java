package com.example.easyexcel.tasks;

import cn.hutool.extra.spring.SpringUtil;
import com.example.easyexcel.service.LikedService;
import com.example.easyexcel.utils.SpringContextJobUtil;
import com.example.easyexcel.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Slf4j
public class LikeTask extends QuartzJobBean {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LikeTask-------- {}", sdf.format(new Date()));
        LikedService likedService = SpringUtils.getBean(LikedService.class);
        //将 Redis 里的点赞信息同步到数据库里
        likedService.transLikedFromRedis2DB();
        likedService.transLikedCountFromRedis2DB();
        log.info("LikeTask完成!-------- {}", sdf.format(new Date()));
    }
}
