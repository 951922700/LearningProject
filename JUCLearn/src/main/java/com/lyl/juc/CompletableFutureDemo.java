package com.lyl.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    /**
     * 异步回调
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"没有返回");
        });

        CompletableFuture<Integer> completableFuture2=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"有返回");
            int i=1/0;
            return 1024;
        });
        completableFuture2.whenComplete((t,u)->{
            System.out.println(""+t);

        }).exceptionally(t->{
            System.out.println("错误"+t.getMessage());
            return 4444;
        }).get();
    }

}
