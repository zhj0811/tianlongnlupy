package com.cctegitc.ai.function.config;

import com.cctegitc.ai.function.util.TimeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2022-06-21
 * @Time: 10:24
 */
@Configuration
@EnableScheduling
public class ScheduleTask {

    //@Scheduled(cron = "0/5 * * * * ?")每5秒执行
    //每月1号10点
    @Scheduled(cron = "0 10 0 1 * ?")
    private void configureTask() {
        System.out.println(TimeUtils.getDate(new Date()));
    }
}
