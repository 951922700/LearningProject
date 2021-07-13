package com.lyl.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void myLock(){
        Thread thread=Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){

        }
        System.out.println(Thread.currentThread().getName()+"\t"+"get lock");
    }

    public void myUnLock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t"+"unlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
           try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
           spinLockDemo.myUnLock();
        },"A").start();

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"B").start();
    }
}
