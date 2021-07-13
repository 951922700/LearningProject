package com.lyl.o2o.controller;


import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.UnitTitle;
import com.lyl.o2o.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/unit")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @RequestMapping(value="/query",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> query(Unit unit){
        Map<String,Object> modelMap=new HashMap<>();
        if(unit.getUid()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","uid为空");
            return modelMap;
        }

        try {
            UnitTitle unitTitle = unitService.queryUnitById(unit);
            if(unitTitle==null){
                modelMap.put("success",false);
                modelMap.put("errMsg","不存在该章节内容");
            }else{
                modelMap.put("success",true);
                modelMap.put("data",unitTitle);
                modelMap.put("errMsg","查询成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }
}
