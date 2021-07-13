package com.lyl.work.service.impl;

import com.lyl.work.dao.MenuDao;
import com.lyl.work.entity.Menu;
import com.lyl.work.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> queryMenuAll() {
        return menuDao.queryMenuAll();
    }
}
