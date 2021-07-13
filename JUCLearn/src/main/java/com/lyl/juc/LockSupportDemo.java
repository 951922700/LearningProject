package com.lyl.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    /**
     *LockSupport
     */
    public static void main(String[] args) {
        Thread a=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t"+"被唤醒");
        },"A");
        a.start();
        try { TimeUnit.SECONDS.sleep(2L); } catch (InterruptedException e) { e.printStackTrace(); }
        Thread b=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"唤醒a");
            LockSupport.unpark(a);
        },"B");
        b.start();
    }
}
