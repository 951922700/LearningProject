package com.lyl.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print{
    private int print=2;
    private int number=1;
    private char ch='A';
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    public void printNumber()throws Exception{
        lock.lock();
          try {
                  //不等于2  等待  不能打印
                  while (print != 2)
                      condition1.await();
                  //可以打印
                  int i = 0;
                  for (; i <= 1; i++) {
                      System.out.print(number + i);
                      print--;
                  }
                  number = number + i;
                  condition2.signal();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             lock.unlock();
          }

    }

    public void printChar()throws Exception{
        lock.lock();
          try {
                  //不等于0  等待  不能打印
                  while (print != 0)
                      condition2.await();
                  //可以打印
                  System.out.print(ch);
                  ch = (char) (ch + 1);
                  //将print置回2
                  print=2;
                  condition1.signal();
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             lock.unlock();
          }
    }
}
public class Question_1 {
    /**
     * 编写两个线程，一个线程打印1~25，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z
     */
    public static void main(String[] args) {
        /*char a='A';
        System.out.println((char) (a+1));*/
        Print print=new Print();
        new Thread(()->{
            try {
                for (int i=0;i<26;i++)
                print.printNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i <26; i++)
                print.printChar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
