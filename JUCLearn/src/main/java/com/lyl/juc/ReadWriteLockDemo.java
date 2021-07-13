package com.lyl.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void get(String key) throws InterruptedException {
         readWriteLock.readLock().lock();
          try {
              System.out.println(Thread.currentThread().getName()+"\t读取数据");
              TimeUnit.MILLISECONDS.sleep(300);
              Object result=map.get(key);
              System.out.println(Thread.currentThread().getName()+"\t读取成功"+result);
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             readWriteLock.readLock().unlock();
          }

    }

    public void put(String key,Object object) throws InterruptedException {
         readWriteLock.writeLock().lock();
          try {
              System.out.println(Thread.currentThread().getName()+"\t写入数据");
              TimeUnit.MILLISECONDS.sleep(300);
              map.put(key,object);
              System.out.println(Thread.currentThread().getName()+"\t写入完成");
          }catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
             readWriteLock.writeLock().unlock();
          }

    }
}
/**
 *
 * 读 读共存
 * 读 写不可
 * 写 写不可
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for (int i = 0; i <5 ; i++) {
            final int temp=i;
            new Thread(()->{
                try {
                    myCache.put(temp+"",temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int i = 0; i <5 ; i++) {
            final int temp=i;
            new Thread(()->{
                try {
                    myCache.get(temp+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
