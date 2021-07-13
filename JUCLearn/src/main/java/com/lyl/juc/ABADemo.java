package com.lyl.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("---------ABA问题产生----------");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"A").start();

        new Thread(()->{
            //保证线程A执行一次ABA操作
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2020)+"\t"+atomicReference.get());
        },"B").start();

        System.out.println("---------ABA问题解决----------");

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+stamp);
            //暂停
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t版本号"+atomicStampedReference.getStamp());
            },"C").start();

        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号"+stamp);
            //暂停
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean result=atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功否"+result);
            System.out.println(Thread.currentThread().getName()+"\t最新值"+atomicStampedReference.getReference()+"\t最新版本号"+atomicStampedReference.getStamp());
        },"D").start();
    }
}
