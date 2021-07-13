package com.lyl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Question:
 * 多线程之间顺序调用 实现A->B->C
 * 三个线程启动
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。。来十轮
 */
class SharedResource{
    private int number=1;//1==A 2==B 3==C

    private Lock lock=new ReentrantLock();
    //三把钥匙
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void print5(){
         lock.lock();
          try {
              //1.不等于1 阻塞并释放锁
              while (number!=1){
                  condition1.await();
              }
              //2.action
              for (int i=1;i<=5;i++){
                  System.out.println(Thread.currentThread().getName()+"\t打印"+i+"次");
              }
              //3.通知
              number=2;
              //精准通知
              condition2.signal();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             lock.unlock();
          }
    }

    public void print10(){
        lock.lock();
        try {
            //1.不等于1 阻塞并释放锁
            while (number!=2){
                condition2.await();
            }
            //2.action
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t打印"+i+"次");
            }
            //3.通知
            number=3;
            //精准通知
            condition3.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //1.不等于1 阻塞并释放锁
            while (number!=3){
                condition3.await();
            }
            //2.action
            for (int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t打印"+i+"次");
            }
            //3.通知
            number=1;
            //精准通知
            condition1.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        SharedResource sharedResource=new SharedResource();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                sharedResource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                sharedResource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                sharedResource.print15();
            }
        },"C").start();

    }
}
