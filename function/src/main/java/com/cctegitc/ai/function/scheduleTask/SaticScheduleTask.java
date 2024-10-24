package com.cctegitc.ai.function.scheduleTask;
import com.cctegitc.ai.function.service.impl.OutCallnotificationsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-07-05
 * @Time: 10:08
 */
@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {


    @Autowired
    OutCallnotificationsServiceImpl outCallnotificationsService;

    //指定时间凌晨0点
    @Scheduled(cron ="0 0 0 * * ?")
    private void configureTasks() {
        outCallnotificationsService.outCallScheduled();
        System.out.println("定时任务刷新----------");
    }


}
