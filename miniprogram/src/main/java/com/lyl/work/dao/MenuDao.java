package com.lyl.work.dao;

import com.lyl.work.entity.Menu;

import java.util.List;

public interface MenuDao {
    /**
     * 查询全部菜谱
     */
    List<Menu> queryMenuAll();
}
