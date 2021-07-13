package com.lyl.work.service;

import com.lyl.work.entity.User;

import java.util.List;

public interface UserService {

    List<User> queryUserAll();

    User queryUserById(String openId);

    void insertUser(User user);

    void updateUser(User user);
}
