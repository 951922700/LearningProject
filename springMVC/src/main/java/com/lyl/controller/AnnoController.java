package com.lyl.controller;

import com.lyl.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(path = "/anno")
@SessionAttributes(value = {"msg"})//相当于把request中的msg（meiemi）存入session域中
public class AnnoController {
    /**
     * 解决前端参数名字与后台形参不一致的问题
     *
     * @return
     */
    @RequestMapping(path = "/testRequestParam")
    public String testRequestParam(@RequestParam(name = "uname") String username) {
        System.out.println("执行了");
        System.out.println(username);
        return "success";
    }

    /**
     * 获取请求体内容
     *
     * @return
     */
    @RequestMapping(path = "/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("执行了");
        System.out.println(body);
        return "success";
    }

    /**
     * PathVariable注解
     * <p>
     * RequestHeader(value="accept")获取请求消息头用法类似
     *
     * @return
     */
    @RequestMapping(path = "/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(name = "id") String id) {
        System.out.println("执行了");
        System.out.println(id);
        return "success";
    }

    /**
     * 获取cookie
     * CookieValue
     *
     * @return
     */
    @RequestMapping(path = "/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String CookieValue) {
        System.out.println("执行了");
        System.out.println(CookieValue);
        return "success";
    }

    /**
     * ModelAttribute
     * 出现在方法上，表示当前方法会在控制器的方法执行之前执行,可以有返回值，也可以没有，看下面例子
     * 出现在参数上，获取指定的数据给参数赋值
     * <p>
     * 应用场景：
     * 当表单提交的数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据
     * such as：当我们在编辑一个用户时，用户有一个创建信息字段，该字段的值是不予许被修改的，在提交表单数据时肯定
     * 没有此字段的内容，一旦更新会把该字段内容置为null
     *
     * @return
     */
    @RequestMapping(path = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user) {
        System.out.println("testModelAttribute执行了");
        System.out.println(user);
        return "success";
    }

    /**
     * 该方法先执行
     * 有返回值，上面参数需要对应User user
     */
    /*@ModelAttribute
    public User showUser(String uname){
        System.out.println("showUSer执行了");
        //首先获取到传过来的某个参数
        //从数据库获取整个信息
        User user=new User();
        user.setAge(20);
        user.setDate(new Date());
        user.setUname(uname);
        return user;
    }*/

    /**
     * 无返回，上面那个方法要加上注解获取值，有返回的不需要
     * @param uname
     * @param map
     */
    @ModelAttribute
    public void showUser(String uname, Map<String, User> map) {
        System.out.println("showUSer执行了");
        //首先获取到传过来的某个参数
        //从数据库获取整个信息
        User user = new User();
        user.setAge(20);
        user.setDate(new Date());
        user.setUname(uname);
        map.put("abc", user);
    }

    /**
     * Model 能以键值对的形式将值传递给下一个界面
     * SessionAttributes
     * @return
     */
    @RequestMapping(path = "/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        //底层会存储到request域对象当中去
        model.addAttribute("msg","meimei");
        return "success";
    }

    /**
     * 取值,,还有一个删除 参数中写SessionStatus status   status.setComplete()就删除会话了
     * @param modelMap
     * @return
     */
    @RequestMapping(path = "/testgetSessionAttributes")
    public String testgetSessionAttributes(ModelMap modelMap) {
      String msg= (String) modelMap.get("msg");
      System.out.println(msg);
      return "success";
    }
}