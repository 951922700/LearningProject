package com.lyl.controller;

import com.lyl.service.DoctorService;
import com.lyl.utils.JSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="医生信息业务的接口", tags= {"医生信息业务的接口"})
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getall")
    public JSONResult getAllDoctorInfo(){
        return JSONResult.ok(doctorService.getAll());
    }
}
