package com.lyl.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonLearn {
    private volatile static SingletonLearn singletonLearn;
    public static SingletonLearn getInstance(){
        if (singletonLearn==null){
            synchronized (SingletonLearn.class){
                if (singletonLearn==null)
                singletonLearn=new SingletonLearn();
            }

        }
        return singletonLearn;
    }

    public SingletonLearn() {
        System.out.println("heihei");
        synchronized (SingletonLearn.class){
            if (singletonLearn!=null)
                throw new RuntimeException("不要破坏单例模式");
        }
    }

    public static void main(String[] args) throws Exception {
     /*   for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                SingletonLearn.getInstance();
            },String.valueOf(i)).start();
        }*/
        SingletonLearn.getInstance();
        Constructor<SingletonLearn> declaredConstructor = SingletonLearn.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);//允许调用私有构造好数
        declaredConstructor.newInstance();
    }
}
