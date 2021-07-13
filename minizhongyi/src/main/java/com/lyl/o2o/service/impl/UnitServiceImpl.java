package com.lyl.o2o.service.impl;

import com.lyl.o2o.dao.UnitDao;
import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.UnitTitle;
import com.lyl.o2o.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;
    @Override
    public UnitTitle queryUnitById(Unit unit) {
        return unitDao.queryUnitById(unit);
    }

    @Override
    public Unit queryUnitByCid(Unit unit) {
        return unitDao.queryUnitByCid(unit);
    }
}
