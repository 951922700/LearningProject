package com.lyl.work.service.impl;


import com.lyl.work.dao.DiseaseDao;
import com.lyl.work.entity.Disease;
import com.lyl.work.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseDao diseaseDao;

    @Override
    public List<Disease> findAll() {
        return diseaseDao.findAll();
    }

    @Override
    public List<Disease> findById(int ID) {
        return diseaseDao.findById(ID);
    }
}
