package com.lyl.work.web;


import com.lyl.work.entity.Menu;
import com.lyl.work.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @ResponseBody
   @RequestMapping(value = "/queryall",method = RequestMethod.POST)
    private Map<String,Object> queryMenuAll() {
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<Menu> menus = new ArrayList<>();

        try{
            menus = menuService.queryMenuAll();
            modelMap.put("data",menus);
            modelMap.put("length",menus.size());
            modelMap.put("success",true);
            //System.out.println(menus);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }
}
