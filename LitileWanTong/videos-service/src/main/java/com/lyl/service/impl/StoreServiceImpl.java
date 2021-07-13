package com.lyl.service.impl;

import com.lyl.mapper.StoreMapper;
import com.lyl.pojo.Store;
import com.lyl.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<Store> getall() {
        return storeMapper.getAllStoreInfo();
    }
}
