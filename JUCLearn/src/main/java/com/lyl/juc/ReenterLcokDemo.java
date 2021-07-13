package com.lyl.juc;

class phone{
    public  synchronized void sendMS(){
        System.out.println(Thread.currentThread().getName()+"sendMS");
        sendEmail();
    }
    public  synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"sendEmail");
    }
}
public class ReenterLcokDemo {

}
