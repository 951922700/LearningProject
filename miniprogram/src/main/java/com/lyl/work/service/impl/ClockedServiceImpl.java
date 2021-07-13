package com.lyl.work.service.impl;

import com.lyl.work.dao.ClockedDao;
import com.lyl.work.entity.Clocked;
import com.lyl.work.service.ClockedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clockedService")
public class ClockedServiceImpl implements ClockedService {

    @Autowired
    private ClockedDao clockedDao;

    @Override
    public Clocked queryClockedByid(String Id) {
        return clockedDao.queryClockedByid(Id);
    }

    @Override
    public void updateClocked(String Id) {
        clockedDao.updateClocked(Id);
    }

    @Override
    public void insertNewClocked(String NewId) {
        clockedDao.insertNewClocked(NewId);
    }

    @Override
    public Integer queryTimes(String Id) {
        return clockedDao.queryTimes(Id);
    }

    @Override
    public void insertNewOpenId(String Id) {
   clockedDao.insertNewOpenId(Id);
    }
}
