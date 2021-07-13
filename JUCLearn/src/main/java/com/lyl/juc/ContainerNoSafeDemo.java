package com.lyl.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContainerNoSafeDemo {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
