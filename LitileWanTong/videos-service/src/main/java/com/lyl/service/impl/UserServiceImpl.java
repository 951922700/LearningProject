package com.lyl.service.impl;

import com.lyl.mapper.*;
import com.lyl.pojo.*;
import com.lyl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BabyinfoMapper babyinfoMapper;

    //自动生成id的工具类
    @Autowired
    private Sid sid;

    /**
     * regist  todo 注册测试
     */
    @Override
    public void registUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public User query(String uid) {
        return userMapper.getOne(uid);
    }

    @Override
    public void addBaby(Babyinfo babyinfo) {
        babyinfoMapper.insert(babyinfo);
    }

    @Override
    public Babyinfo queryBaby(String uid) {
        return babyinfoMapper.queryBaby(uid);
    }
}
