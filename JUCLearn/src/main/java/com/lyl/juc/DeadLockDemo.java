package com.lyl.juc;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有:"+lockA+"\t尝试获得"+lockB);
            try { TimeUnit.SECONDS.sleep(2L); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t获得"+lockB);
            }
        }
    }
}
/**
 * 死锁编码
 */
public class DeadLockDemo {
    /**
     * jps -l查看进程
     * jstack  进程号  查看异常
     * @param args
     */
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLockThread(lockA,lockB),"A").start();
        new Thread(new HoldLockThread(lockB,lockA),"B").start();
    }
}
