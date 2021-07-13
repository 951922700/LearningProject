package com.lyl.juc;

import java.sql.Time;
import java.util.concurrent.*;

public class MyThreadPoolDemo {
    /**
     * 线程池
     */
    public static void main(String[] args) {
        /**
         * AbortPolicy
         * CallerRunsPolicy
         * DiscardPolicy
         * DiscardOldestPolicy
         */
        Runtime.getRuntime().availableProcessors();//获取当前运行计算机处理器数量
        ExecutorService executorService=new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try{
            for (int i = 0; i < 10; i++) {
                //参数是Runnable
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();//关闭
        }
    }

    private static void initPool(ExecutorService executorService) {
        //执行长期任务性能好，创建一个线程池，一池有n个固定的线程
        //ExecutorService executorService= Executors.newFixedThreadPool(5);//有5个线程,类似一个银行有5个受理窗口
        //ExecutorService executorService= Executors.newSingleThreadExecutor();//有1个线程,类似一个银行有1个受理窗口
        //ExecutorService executorService= Executors.newCachedThreadPool();//有n// 个线程,类似一个银行有n个受理窗口
        try{
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(2);
                //参数是Runnable
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();//关闭
        }
    }
}
