package com.lyl.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
@AllArgsConstructor
@Getter
class User1{
    String username;
    int age;
}
/**
 * 原子引用
 */
public class AtomicReferenceDemo {


    public static void main(String[] args) {
        User1 z3=new User1("z3",21);
        User1 li4=new User1("li4",25);
        AtomicReference<User1> atomicReference=new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, li4));
    }
}
