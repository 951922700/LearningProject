package com.lyl.work.service.impl;

import com.lyl.work.dao.SportDao;
import com.lyl.work.entity.Sport;
import com.lyl.work.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sportService")
public class SportServiceImpl implements SportService {

    @Autowired
    private SportDao sportDao;

    @Override
    public List<Sport> findAll() {
        return sportDao.findAll();
    }

    @Override
    public List<Sport> findById(int ID) {
        return sportDao.findById(ID);
    }

}
