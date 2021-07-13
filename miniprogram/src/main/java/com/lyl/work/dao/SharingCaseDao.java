package com.lyl.work.dao;

import com.lyl.work.entity.SharingCase;

public interface SharingCaseDao {
    /**
     *根据案例id查询
     * @param sharingCase
     * @return
     */
    SharingCase queryById(SharingCase sharingCase);
}
