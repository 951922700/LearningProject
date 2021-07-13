package com.lyl.controller;

import com.lyl.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/excep")
public class Excep_lanController {
    /**
     * 浏览器-》前端控制器-》web-》service-》dao-》
     *   （前端控制器调用异常处理器）       (往前一个抛出异常)
     *   如果没使用前端控制器，异常会直接抛出到浏览器
     * @return
     */
    @RequestMapping("/testException")
    public String testException() throws SysException{
        System.out.println("异常执行");
        //模拟异常

        try {
            int a=10/0;
        } catch (Exception e) {
            //打印异常
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("皮卡丘不听话出现异常");
        }

        return "success";
    }

    /**
     * 拦截器
     * 1.需要编写拦截器类,实现HandleInterceptor接口
     * 2.配置
     * @return
     */
    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("testInterceptor执行");
        return "success";
    }
}
