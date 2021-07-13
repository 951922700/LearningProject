package com.lyl.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG=true;//保证可见性
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd()throws Exception{
        String data=null;
        boolean retValue=false;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"生产成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"生产失败");
            }
            try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+"停工");
    }

    public void myConsumer()throws Exception{
        String retValue=null;
        while (FLAG){
            retValue = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null==retValue){
                System.out.println("修改前flag值\t"+FLAG);
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t超时2s没消费");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费\t"+retValue);
        }
    }
    public void stop(){
        FLAG=false;
    }
}
/**
 * 生产者消费者  阻塞队列版
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"生产者").start();

        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费者").start();
        try { TimeUnit.SECONDS.sleep(5L); } catch (InterruptedException e) { e.printStackTrace(); }
        myResource.stop();
    }

}
