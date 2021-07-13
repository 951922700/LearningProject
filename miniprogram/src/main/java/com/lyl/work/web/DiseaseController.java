package com.lyl.work.web;

import com.lyl.work.entity.Disease;
import com.lyl.work.entity.Sport;
import com.lyl.work.service.DiseaseService;
import com.lyl.work.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;
    @ResponseBody
    @RequestMapping(value ="/queryall",method = RequestMethod.POST)
    private Map<String,Object> queryDiseaseAll(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Disease> diseases = new ArrayList<>();

        try{
            diseases = diseaseService.findAll();
            modelMap.put("data", diseases);
            modelMap.put("length",diseases.size());
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }

        return modelMap;

    }



    @ResponseBody
    @RequestMapping(value ="/queryById",method = RequestMethod.POST)
    private Map<String,Object> querySportById(@RequestParam("id") int id){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Disease> diseases = new ArrayList<>();

        try{
            diseases = diseaseService.findById(id);
            modelMap.put("data",diseases);
            modelMap.put("length",diseases.size());
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }

        return modelMap;

    }


}
