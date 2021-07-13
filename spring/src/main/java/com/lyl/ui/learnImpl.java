package com.lyl.ui;

public class learnImpl implements learn {
    public void saveAccount() {
        System.out.println("执行保存");
    }

    public void updateAccount(int i) {
        System.out.println("执行更新"+i);
    }

    public int deleteAccount() {
        System.out.println("执行删除");
        return 0;
    }
}
