package com.lyl.work.web;

import com.lyl.work.entity.Sport;
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
@RequestMapping(value = "/sport")
public class SportController {

    @Autowired
    private SportService sportService;
    @ResponseBody
    @RequestMapping(value ="/queryall",method = RequestMethod.POST)
    private Map<String,Object> querySportAll(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Sport> sports = new ArrayList<>();

        try{
            sports = sportService.findAll();

            modelMap.put("data",sports);
            modelMap.put("length",sports.size());
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
        List<Sport> sports = new ArrayList<>();

        try{
            sports = sportService.findById(id);

            modelMap.put("data",sports);
            modelMap.put("length",sports.size());
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }

        return modelMap;

    }


}
