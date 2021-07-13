package com.lyl.work.service.impl;

import com.lyl.work.dao.UserDao;
import com.lyl.work.entity.User;
import com.lyl.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUserAll() {
        return userDao.queryUserAll();
    }

    @Override
    public User queryUserById(String openId) {
        return userDao.queryUserById(openId);
    }

    @Override
    public void insertUser(User user) {
         userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
