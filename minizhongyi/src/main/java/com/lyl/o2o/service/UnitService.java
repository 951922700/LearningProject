package com.lyl.o2o.service;

import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.UnitTitle;

public interface UnitService {
    UnitTitle queryUnitById(Unit unit);

    /**
     * 根据cid查某页内容
     */
    Unit queryUnitByCid(Unit unit);
}
