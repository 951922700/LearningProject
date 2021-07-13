package com.lyl.mapper;

import com.lyl.pojo.Store;
import com.lyl.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMapper extends MyMapper<Store> {
    public List<Store> getAllStoreInfo();
}