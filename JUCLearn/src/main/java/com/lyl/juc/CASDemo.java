package com.lyl.juc;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * CAS比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 200)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
