package com.lyl.juc;

import com.lyl.myenum.CountryEnum;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();//计数器-1
            }, CountryEnum.forEach_CountryEnum(i).getRetString()).start();
        }
        countDownLatch.await();//阻塞
        System.out.println("锁门");
    }

    private static void closeDoor() {
        //提取代码成方法 ctrl alt m
        //这串代码没办法保证执行完6个线程再执行主线程  顺序控制
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println("锁门");
    }
}
