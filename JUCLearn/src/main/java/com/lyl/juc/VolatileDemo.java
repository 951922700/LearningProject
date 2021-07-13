package com.lyl.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number=0;
    AtomicInteger atomicInteger=new AtomicInteger();
    public void add(){
        number=60;
    }

    public void addPlusPlus(){
        number++;
    }

    public void addAtomic(){
        atomicInteger.getAndIncrement();//i++
    }
}
public class VolatileDemo {
    public static void main(String[] args) {
        //seeOkVolatile();
        MyData myData=new MyData();
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        //默认存在两个线程  主线程以及后台gc线程
        while (Thread.activeCount()>2){
            Thread.yield();//让出线程权
        }
        System.out.println( Thread.currentThread().getName()+"\tValue:"+myData.number);
        System.out.println( Thread.currentThread().getName()+"\tValue:"+myData.atomicInteger);
    }

    //可见性
    private static void seeOkVolatile() {
        MyData myData=new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\tcome in");
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
           myData.add();
            System.out.println(Thread.currentThread().getName()+"finished"+myData.number);
        },"A").start();

        while (myData.number==0){}
        System.out.println("over now");
    }
}
