package com.lyl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Producer producer=new ProducerImpl() ;//要求是final
        /**
         * 动态代理：
         * 特点：字节码随用 ，随创建 随加载
         * 作用：修改源码的基础上，对方法增强
         * 分类：
         *      基于接口的动态代理(代理的对象必须实现了某个接口)
         *      基于子类的动态代理
         *  基于接口：
         *      涉及的类：Proxy
         *      提供者：JDK官方
         *  如何创建代理对象：Proxy中的newProxyInstance
         *  创建代理类的要求：
         *          被代理类最少实现一个接口，如果没有则不能使用
         *   newProxyInstance参数：
         *   classLoader:类加载器
         *          用于加载代理对象字节码的，和被代理对象使用相同的类加载器
         *   class[]:字节码数组
         *          用于让代理对象和被代理对象有相同的方法
         *   InvocationHandler
         *          用于提供增强的代码
         *          让我们如何代理，一般是一个该接口的实现类，通常情况下都是匿名内部类，但不固定
         */
        Producer proxyProducer= (Producer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(), new InvocationHandler() {
                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     * 方法参数含义
                     * @param proxy 代理对象的引用
                     * @param method 当前执行的方法
                     * @param args  当前执行方法所需的参数
                     * @return      和被代理对象有相同的返回值
                     * @throws  Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强代码
                        Object returnValue=null;
                        //1.获取方法执行的参数
                        Float money=(Float)args[0];
                        //判断当前方法是不是销售
                        if("saleProduct".equals(method.getName())){
                            returnValue= method.invoke(producer,money*0.8f);//第一个参数要是final
                        }
                        return  returnValue;
                    }
                });
        proxyProducer.saleProduct(10000f);
    }
}
