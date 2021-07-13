package com.lyl.Controller;

import com.lyl.service.ASyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AsyncController {
    @Autowired
    ASyncService aSyncService;

    @GetMapping("/async")
    public String hello(){
        aSyncService.hello();
        return "success";
    }
}
