package com.lyl.work.dao;

import com.lyl.work.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有用户
     */
    List<User> queryUserAll();

    /**
     * 根据openId查询用户
     */
    User queryUserById(String openId);

    /**
     * 创建用户
     */
    void insertUser(User user);

    /**
     * 更新用户信息
     */
    void updateUser(User user);
}
