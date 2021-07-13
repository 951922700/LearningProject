package com.lyl.o2o.service.impl;

import com.lyl.o2o.dao.UserDao;
import com.lyl.o2o.entity.User;
import com.lyl.o2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User selectByAccount(User user) {
        return userDao.selectByAccount(user);
    }

    @Override
    public void updateQuestionCollect(User user) {
        userDao.updateQuestionCollect(user);
    }

    @Override
    public void updateUnitCollect(User user) {
        userDao.updateUnitCollect(user);
    }

    @Override
    public User selectAccount(User user) {
        return userDao.selectAccount(user);
    }

    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }
}
