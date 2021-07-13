package com.lyl.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("8");
        });
        for (int i = 0; i <7 ; i++) {
            final int tempint=i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"执行"+tempint);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }
}
