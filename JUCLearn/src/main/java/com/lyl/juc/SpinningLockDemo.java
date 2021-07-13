package com.lyl.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

class SpinningLock{
    private AtomicReference<Thread> ref=new AtomicReference<>();

    public void lock(){
        Thread thread=Thread.currentThread();
        while (!ref.compareAndSet(null,thread)){
            //自旋
        }
    }

    public void unLock(){
        Thread thread=Thread.currentThread();
        ref.compareAndSet(thread,null);
    }
}
public class SpinningLockDemo {
    /**
     * 手写一个自旋锁
     */
     static  int count=0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService=new ThreadPoolExecutor(100,
                100,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        CountDownLatch countDownLatch=new CountDownLatch(100);
        SpinningLock spinningLock=new SpinningLock();
        for (int i = 1; i <=100 ; i++) {
           executorService.execute(()->{
               spinningLock.lock();
               count++;
               spinningLock.unLock();
               countDownLatch.countDown();
           });
        }
        countDownLatch.await();
        System.out.println(count);
        executorService.shutdown();
    }

}
