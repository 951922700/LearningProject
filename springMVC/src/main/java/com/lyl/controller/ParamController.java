package com.lyl.controller;

import com.lyl.domain.Account;
import com.lyl.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping(path = "/param")
public class ParamController {
    @RequestMapping(path = "/testParam")
    public String testParam(String username){
        System.out.println("执行了");
        System.out.println(username);
        return "success";
    }

    @RequestMapping(path = "/saveAccount")
    public String saveAccount(Account account){
        System.out.println("执行了");
        System.out.println(account);
        return "success";
    }
    @RequestMapping(path = "/saveUser")
    public String saveUser(User user){
        System.out.println(user);
        return "success";
    }
    @RequestMapping(path = "/testServlet")
    public String saveTestServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);
        HttpSession session=request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        System.out.println(response);
        return "success";
    }
}
