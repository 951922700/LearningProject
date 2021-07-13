package com.lyl.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //@GetMapping  @DeleteMapping @PutMapping
    @PostMapping(value = "/user/login")
    public String login(String username, String password, Map<Object,String> map, HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            /*登录成功,防止表单重复提交，可以重定向到主页，如果没有这样进行映射的话，在登录成功后刷新界面，会询问
            是否要重新提交表单点确定才能刷新*/
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
            /*而为什么要进行配置一个映射先呢，因为redirect:/dashboard的话
            theamleaf是无法进行视图解析的
            但是这样直接进行/main.html会直接到这个界面而跳过登录
            所以要配置拦截器
            */

        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }

    }
}
