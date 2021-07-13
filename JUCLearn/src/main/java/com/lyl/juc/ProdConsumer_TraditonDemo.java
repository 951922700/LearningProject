package com.lyl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedData{
    private int number=0;
    private Lock lock=new ReentrantLock(true);
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();

    public void add()throws Exception{
         lock.lock();
          try {
              //防止虚假唤醒 用while
              while (number!=0){
                  //等待 不能生产
                  condition1.await();
              }
              number++;
              System.out.println(Thread.currentThread().getName()+"\t生产");
              condition2.signal();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             lock.unlock();
          }
    }

    public void dec()throws Exception{
         lock.lock();
          try {
              while (number!=1){
                  condition2.await();
              }
              number--;
              System.out.println(Thread.currentThread().getName()+"\t消费");
              condition1.signal();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             lock.unlock();
          }

    }
}
/**
 * 一个初始值为0的变量，两个线程对其交替操作 一个+1一个-1  5轮
 */
public class ProdConsumer_TraditonDemo {
    public static void main(String[] args) {
        SharedData sharedData=new SharedData();
        new Thread(()->{
            for (int i = 0; i <2 ; i++) {
                try {
                    sharedData.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <2 ; i++) {
                try {
                    sharedData.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <2 ; i++) {
                try {
                    sharedData.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i <2 ; i++) {
                try {
                    sharedData.dec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
