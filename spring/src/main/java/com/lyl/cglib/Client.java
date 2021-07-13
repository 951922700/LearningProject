package com.lyl.cglib;

import com.lyl.proxy.Producer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final ProducerImpl producer=new ProducerImpl() ;//要求是final
        /**
         * 动态代理：
         * 特点：字节码随用 ，随创建 随加载
         * 作用：修改源码的基础上，对方法增强
         * 分类：
         *      基于接口的动态代理(代理的对象必须实现了某个接口)
         *      基于子类的动态代理
         *  基于子类：
         *      涉及的类：Proxy
         *      提供者：第三方cglib库
         *  如何创建代理对象：Enhancer中的create方法
         *  创建代理类的要求：
         *          被代理类不能是final修饰的类
         *   create参数：
         *   Class:指定被代理对象的字节码
         *   Callback:用于提供增强的代码：
         *   一般写的都是该接口的子接口实现类：MethodInterceptor方法拦截
         *
         */
        ProducerImpl cglibProducer=(ProducerImpl) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param proxy 代理对象的引用
             * @param method 当前执行的方法
             * @param args 当前执行方法所需的参数
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //提供增强代码
                Object returnValue=null;
                //1.获取方法执行的参数
                Float money=(Float)args[0];
                //判断当前方法是不是销售
                if("saleProduct".equals(method.getName())){
                    returnValue= method.invoke(producer,money*0.8f);
                }
                return  returnValue;
            }
        });
        cglibProducer.saleProduct(12000f);
    }
}
