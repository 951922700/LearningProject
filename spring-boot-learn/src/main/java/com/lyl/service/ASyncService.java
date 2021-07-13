package com.lyl.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ASyncService {

    /**
     * 在controller调用这个方法的时候如果是同步  必须等待三秒才会返回到success界面
     * 如果弄成异步的话这个方法的调用和return语句就是异步的 就能立马返回到success然后3秒后控制台打印
     * 要在主配置类加上EnablAsync
     */
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中······");
    }
}
