package com.lyl.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class  MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("come in here");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {
    /**
     * 实现多线程的三种方法
     * 1.继承Thread
     * 2.实现Runnable接口
     * 3.实现Callable
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=new FutureTask(new MyThread());
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        System.out.println(futureTask.get());//输出返回值  会阻塞等待线程完成才输出结果
        System.out.println("计算完成");

    }
}
