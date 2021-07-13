package com.lyl.juc;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail()throws Exception{
        //Thread.sleep(4000);
        //TimeUnit时间类管理时间
        try {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("sendEmail");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public  synchronized void sendMS(){
        System.out.println("sendMS");
    }

    public void hello(){
        System.out.println("hello");
    }
}

/**
 * 问题：
 * 1.标准访问 先打印邮件还是短信  不一定
 * 2.邮件方法暂停4s，请问先打印邮件还是短信  邮件
 * 3.新增普通方法 输出什么？ hello
 * 4.两部手机 先邮件还是短信  短信
 * 5.两个静态同步方法 同一部手机 先打印邮件还是手机  邮件
 * 6.两个静态同步方法 两部手机 先打印邮件还是手机  邮件  静态资源属于类的
 * 7.一个普通同步方法，一个静态同步方法 一部手机 先打印邮件还是手机  短信
 * 8.一个普通同步方法，一个静态同步方法 两部手机 先打印邮件还是手机  短信
 * 静态就是类锁  普通就是对象锁  两个各自锁各自的
 */
public class Lock8 {
    public static void main(String[] args) throws Exception{
        Phone phone=new Phone();
        Phone phone1=new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(200);
        new Thread(()->{
            phone1.sendMS();
        },"B").start();
    }
}
