package com.lyl.work.service.impl;

import com.lyl.work.dao.SharingCaseDao;
import com.lyl.work.entity.SharingCase;
import com.lyl.work.service.SharingCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SharingCaseServiceImpl implements SharingCaseService {
    @Autowired
    private SharingCaseDao sharingCaseDao;

    @Override
    public SharingCase queryById(SharingCase sharingCase) {
        return sharingCaseDao.queryById(sharingCase);
    }
}
