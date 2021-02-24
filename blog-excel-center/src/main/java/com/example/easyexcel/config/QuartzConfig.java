package com.example.easyexcel.config;

import com.example.easyexcel.tasks.LikeTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.TimeZone;

/**
 * <p>
 * 定时任务配置
 * <p>
 *
 * @author zhenhuaxiang
 * @since 2021-02-08
 */
@Configuration
public class QuartzConfig {
    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";
    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }
    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(24)  //24小时执行一次
                .repeatForever();
        long currentTime = System.currentTimeMillis(); // 当前时间毫秒数
        long zero=currentTime/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        System.out.println("今天零点时间毫秒数:"+zero);
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .startAt(new Date(zero))
                .build();
    }
}
