package com.lyl.work.web;

import com.lyl.work.entity.SharingCase;
import com.lyl.work.service.SharingCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/case")
public class SharingCaseController {

    @Autowired
    private SharingCaseService sharingCaseService;


    @ResponseBody
    @RequestMapping(value="/queryById")
    public Map<String,Object> queryById(SharingCase sharingCase){
        Map<String,Object> modelMap=new HashMap<>();
        if(sharingCase.getId()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有传id");
            return modelMap;
        }

        try {
            SharingCase aCase = sharingCaseService.queryById(sharingCase);
            modelMap.put("success",true);
            modelMap.put("data",aCase);
            modelMap.put("errMsg","查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }
}
