package com.lyl.Controller;

import com.lyl.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

//@ResponseBody  //可以写在这里表示这个类的所有返回的数据给浏览器，如果是对象自动转换成json数据
@Controller     //这两个注解可以用@RestController
public class HelloController {
    //@ResponseBody
    /*@RequestMapping({"/","/login.html"})
    public String index(){
        return "login";
    }*/

    //要映射到template下的界面不需要加responseBody进行数据解析
    //直接把文件放在templates下，只要导入了thymeleaf就能直接跳转到某个界面
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好啊</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi"));
        //classpath:/template/success
        return "success";
    }

    //测试自定义客户端页面错误返回的数据
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello";
    }
}
