package com.lyl.juc;

@FunctionalInterface//自动加上的函数式接口
interface Foo{
    //public void say();
    public int said(int x,int y);

    default int div(int x,int y){
        return x/y;
    }

    public static int mul(int x,int y){
        return x*y;
    }
}

/***
 * 函数式接口
 * 只能有一个函数
 *
 * java8之前接口不能实现
 * java8之后允许部分实现 用default  可以有多个
 * public static  也可以多个
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo=()->{
            System.out.println("hhh");
        };*/
        Foo foo=( x, y)->{
            System.out.println("hhh");
            return x+y;
        };
        foo.said(1,2);
    }
}
