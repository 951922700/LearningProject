package com.lyl.mapper;

import com.lyl.pojo.Babyinfo;
import com.lyl.pojo.User;
import com.lyl.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends MyMapper<User> {
    public User getOne(String uid);


}