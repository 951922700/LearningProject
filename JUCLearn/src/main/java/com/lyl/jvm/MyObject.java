package com.lyl.jvm;

public class MyObject {
    public static void main(String[] args) {
        Object object=new Object();//这是启动类加载器加载的
        //输出null  因为启动类加载器用c写的
        System.out.println(object.getClass().getClassLoader());


        MyObject myObject=new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
        
    }
}
