package com.lyl.o2o.dao;

import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.UnitTitle;

public interface UnitDao {
    /**
     * 查询单元下每一页的内容
     * @param unit
     * @return
     */
    UnitTitle queryUnitById(Unit unit);

    /**
     * 根据cid查某页内容
     */
    Unit queryUnitByCid(Unit unit);
}
