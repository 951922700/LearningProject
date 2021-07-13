package com.lyl.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditoner{
    private int number=0;

    private Lock lock=new ReentrantLock();

    private Condition condition=lock.newCondition();
    /**
     * 虚假唤醒
     * 假设此刻number==1
     * 线程A 此时进入了第一个判断里面 但是没有执行wait这个时候系统调度
     * 此时线程B 执行因为number==1不会进入wait此时number--变成0 并调度到A
     * 此时A开始执行wait并释放了锁
     * 系统调度到线程C 因为number==0 C不会进入判断 并让number进行+1
     * 此时C唤醒A
     * A继续执行后面的代码 即number++ 此时number变成2  程序逻辑错误
     *
     * 解决方法：
     * 不能在if代码块中进行wait操作应该用while即被唤醒后应该再判断一次
     */
   /* public synchronized void increment() throws InterruptedException {
        //1.判断
        while (number==1)
            this.wait();//会释放锁
        //2.工作
        number++;
        System.out.println(Thread.currentThread().getName()+"\t+1"+"\t"+number);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断
        while (number==0)
            this.wait();
        //2.工作
        number--;
        System.out.println(Thread.currentThread().getName()+"\t-1"+"\t"+number);
        //3.通知
        this.notifyAll();
    }*/

    public  void increment() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number==1)
                condition.await();//会释放锁
            //2.工作
            number++;
            System.out.println(Thread.currentThread().getName()+"\t+1"+"\t"+number);
            //3.通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void decrement() throws InterruptedException {
        lock.lock();
         try {
             //1.判断
             while (number==0)
                 condition.await();
             //2.工作
             number--;
             System.out.println(Thread.currentThread().getName()+"\t-1"+"\t"+number);
             //3.通知
             condition.signalAll();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
            lock.unlock();
          }

    }
}
/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，10轮，变量初始值为0
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditoner airConditoner=new AirConditoner();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airConditoner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airConditoner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airConditoner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airConditoner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
