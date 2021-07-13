package com.lyl.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * 定时任务
     * second,minute,hour,day of month,month,day of week
     * 0 * * * * MON-STA
     * 周一到周六每个0秒的时刻都调用
     * 需要在主配置开启EnableScheduling
     */
    @Scheduled(cron = "0 * * * * MON-SAt")
    public void hello(){
        System.out.println("hello`````");
    }
}
