package com.lyl.mapper;

import com.lyl.pojo.SearchRecords;
import com.lyl.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
    public List<String> getHotWords();
}