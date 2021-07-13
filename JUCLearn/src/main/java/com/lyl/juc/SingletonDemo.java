package com.lyl.juc;

/**
 * 单例模式
 */
public class SingletonDemo {
    private volatile static SingletonDemo instance=null;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t构造方法执行");
    }

    //DCL(Double Check Lock)双端检锁机制
    public static SingletonDemo getInstance(){
        /**
         * 保证只是第一次创建实例对象的时候进行同步，而不是每次执行都进行同步准备
         */
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null)
                instance=new SingletonDemo();
            }

        }
        /**
         * 创建对象底部有三部
         * 1.分配内存
         * 2.初始化对象
         * 3.设置instance指向内存地址  指向内存了就!=null  指令重排会导致别的线程返回一个空的东西
         */
        return instance;
    }
    public static void main(String[] args) {

        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
                System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
            },String.valueOf(i)).start();
        }
    }
}
