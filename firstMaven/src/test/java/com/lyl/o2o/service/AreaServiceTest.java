package com.lyl.o2o.service;

import com.lyl.o2o.BaseTest;
import com.lyl.o2o.entity.Area;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList()
    {
        List<Area> areaList=areaService.getAreaList();
        assertEquals("广州",areaList.get(0).getAreaName());
    }
}
