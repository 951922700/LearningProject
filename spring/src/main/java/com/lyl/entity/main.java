package com.lyl.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    /**
     * ApplicationContext的三个常用实现类
     *  ClassPathXmlApplicationContext 可以加载类路径下的的配置文件，要求配置文件必须在类路径下
     *  FileSystemXmlApplicationContext 可以加载磁盘任意路径下的配置文件（需要有权限）
     *  AnnotationConfigApplicationContext 用于读取注解创建容器
     *
     *  核心容器两个接口引发的问题
     *  ApplicationContext: 单例
     *      它在构建核心容器的时候，创建对象采取的策略是采用立即加载的方式，也就是说，只要一读取完配置文件马上就创建配置文件的配置的对象
     *  BeanFactory:    多例
     *      延迟加载，也就是说，什么时候根据id获取对象什么时候才创建对象
     * @param args
     */
    public static void main(String[] args) {
        //获取核心容器对象
        //ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //2根据id获取bean对象
        bean bean=(bean) ac.getBean("bean3");
        ac.close();
        System.out.println(bean);

    }
}
