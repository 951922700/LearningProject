package com.lyl.controller;

import com.lyl.pojo.Store;
import com.lyl.pojo.vo.StoreVo;
import com.lyl.service.StoreService;
import com.lyl.utils.JSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="门店业务的接口", tags= {"门店业务的接口"})
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/getall")
    public JSONResult getAllStoreInfo(){
        List<Store> stores = storeService.getall();
        List<StoreVo> storeVos=new ArrayList<>();
        for (int i=0;i<stores.size();i++){
            List<String> temp=new ArrayList<>();
            for (int j=0;j<stores.get(i).getImages();j++){
                int t=j+1;
                temp.add("https://ruxianai.picp.vip/image/"+stores.get(i).getSid()+"store"+t+".jpg");
            }
            StoreVo storeVo=new StoreVo();
            BeanUtils.copyProperties(stores.get(i),storeVo);
            storeVo.setPath(temp);
            storeVos.add(storeVo);
        }
        return JSONResult.ok(storeVos);
    }
}
