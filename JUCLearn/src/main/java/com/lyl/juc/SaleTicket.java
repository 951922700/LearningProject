package com.lyl.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 高内聚低耦合
 * 线程 操作 资源类
 *
 * 三个售票员 卖出 30张票
 */
class Ticket{//资源类
    private int number=30;

    private Lock lock=new ReentrantLock();//实现可重入锁
    /**
     * 用synchronized锁会直接锁住一个方法全部代码没办法细锁
     */

    public  void saleTicket(){
        //这段代码可以加一个live template
        lock.lock();
        try{
            if (number>0)
            {
                //Thread.currentThread().getName()  常用代码可以自己设置快捷键live template
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+(number--)+"张票"+"\t还剩下"+number+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();

        //new Thread(runnable,threadName)
        //用lambda
        new Thread(()->{for (int i=1;i<40;i++) ticket.saleTicket();},"A").start();
        new Thread(()->{for (int i=1;i<40;i++) ticket.saleTicket();},"A").start();
        new Thread(()->{for (int i=1;i<40;i++) ticket.saleTicket();},"A").start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<40;i++){
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<40;i++){
                    ticket.saleTicket();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<40;i++){
                    ticket.saleTicket();
                }
            }
        },"C").start();*/
    }
}
