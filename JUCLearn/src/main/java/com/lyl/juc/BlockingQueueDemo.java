package com.lyl.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(1);
        blockingQueue.put("1");//无返回值
        System.out.println(blockingQueue.take());
    }
}
