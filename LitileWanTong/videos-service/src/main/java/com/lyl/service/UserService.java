package com.lyl.service;


import com.lyl.pojo.Babyinfo;
import com.lyl.pojo.User;


import java.util.List;

public interface UserService {


    /**
     * 注册  传入uid以及基本信息   头像设置默认路径
     */
    public void registUser(User user);

    /**
     * 获取所有用户用于判断是否已经有该用户
     */
    public List<User> getAll();

    /**
     * 查询用户
     */
    public User query(String uid);

    /**
     * 宝宝信息插入
     */
    public void addBaby(Babyinfo babyinfo);
    /**
     * 宝宝信息查询
     */
    public Babyinfo queryBaby(String uid);
}
