package com.lyl.work.service;

import com.lyl.work.entity.Sport;

import java.util.List;

public interface SportService {
    /**
 * 查询全部Sport运动
 */
List<Sport> findAll();


    /**
     * 通过ID查找
     */
    List<Sport> findById(int ID);

}
