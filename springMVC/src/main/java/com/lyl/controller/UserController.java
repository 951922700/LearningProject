package com.lyl.controller;

import com.lyl.domain.User;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法执行了");
        User user=new User();
        user.setUname("啊啊");
        model.addAttribute("user",user);
        return "success";
    }

    /**
     * 返回值为void
     * 路径：请求转发一次请求，不用编写项目名字
     * 重定向需要项目名字，getContextPath =http://localhost:8080/springMVC_war+"/xxx.jsp"
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid方法执行了");
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
     /*   //重定向
        response.sendRedirect(request.getContextPath()+"/index.jsp");*/
     //设置中文乱码,直接响应，直接在页面输出
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("你好");
        return;
    }

    /**
     * 返回ModelandView,作用就是存对象加跳转
     * @param request
     * @param response
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("testModelAndView方法执行了");
        ModelAndView mv=new ModelAndView();
        User user=new User();
        user.setUname("啊啊");
        //把user对象存储到mv对象中，也会把user对象存入到request对象
        mv.addObject("user",user);
        //跳转到哪个页面,视图解析器会补全路径
        mv.setViewName("success");
        return mv;
    }

    /**
     * 使用关键字的方式进行转发或者重定向
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect方法执行了");
        //请求转发
        return "forward:/WEB-INF/pages/success.jsp";
        //重定向
        //return "redirect:/index.jsp";
    }

    /**
     * 模拟异步请求响应json数据,前端ajax的data可以用body接收
     * @RequestMapping("/testAjax")
     *     public void testAjax(@RequestBody String body){
     *         System.out.println("testAjax方法执行");
     *         System.out.println(body);
     *     }
     * 要想前端传来的数据直接封装到bean，需要导入额外jar包jackson（bean<- ->json互相转换）
     * 此时用requesBody修饰一个类，返回这个类springmvc就会返回json形式的类
     * 加上responseBody注解
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行");
        System.out.println(user);
        //做响应,模拟查询数据库
        user.setDate(new Date());
        user.setUname("aa");
        return user;
    }
}
