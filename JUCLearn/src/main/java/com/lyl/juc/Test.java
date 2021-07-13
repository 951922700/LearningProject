package com.lyl.juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list= new ArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                for (int j = 0; j <10 ; j++) {
                    list.add(j);
                    System.out.println(list);
                }
            },String.valueOf(i)).start();
        }
    }
}
