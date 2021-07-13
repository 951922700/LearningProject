package com.lyl.myenum;
interface A{
    default void eat(){
        System.out.println("2");
    }
}
interface B{
    default void eat(){

    }
}

interface C extends A,B{
    default void eat(){
        B.super.eat();
    }
}
public class Me implements A,B{
    @Override
    public void eat() {
        A.super.eat();
    }
    public static void main(String[] args) {
        new Me().eat();
    }
}
