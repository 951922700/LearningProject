package com.lyl.work.service;

import com.lyl.work.entity.Disease;

import java.util.List;

public interface DiseaseService {


    /**
     * 查询全部Disease运动
     */
    List<Disease> findAll();


    /**
     * 通过ID查找
     */
    List<Disease> findById(int ID);
}
