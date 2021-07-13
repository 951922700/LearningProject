package com.lyl.mapper;

import com.lyl.pojo.Babyinfo;
import com.lyl.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BabyinfoMapper extends MyMapper<Babyinfo> {
    public Babyinfo queryBaby(String uid);
}