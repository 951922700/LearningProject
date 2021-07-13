package com.lyl.work.web;

import com.lyl.work.entity.User;
import com.lyl.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value="/queryall",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> queryUserAll(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<User> users =new ArrayList<>();
        try {
            users =userService.queryUserAll();
            modelMap.put("data",users);
            modelMap.put("length",users.size());
            modelMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }


    /**
     * 根据openId查询用户
     */
    @RequestMapping(value = "/queryone",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> queryUserById(String openId){
       Map<String,Object> modelMap=new HashMap<String,Object>();
         User user;
         if(openId==null){
             modelMap.put("errMsg","openId为空,或者参数类型错误");
             modelMap.put("success",false);
             return modelMap;
         }
        try {
            user =userService.queryUserById(openId);
            System.out.println(user);
            modelMap.put("data",user);
            modelMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
      // String openId=user.getOpenId();
        /* System.out.println(openId);*/

        return modelMap;
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Map<String,Object> insertUser(User user){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        /**
         * 判断重要的三个参数是否为空
         */
        if(user.getOpenId()==null){
            modelMap.put("errMsg","没有传openId");
            modelMap.put("success",false);
            return modelMap;
        }else if(user.getUserName()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有传userName");
            return modelMap;
        }else if(user.getUserImg()==null){
            modelMap.put("success",false);
            modelMap.put("{errMsg","没有传userImg");
            return modelMap;
        }
        try{
            User user1 = userService.queryUserById(user.getOpenId());
            if (user1==null){
                //openId不存在
                try {
                    user.setCreateTime(new Date());
                    userService.insertUser(user);
                    modelMap.put("success",true);
                    modelMap.put("message","创建新用户成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                    modelMap.put("success",false);
                    modelMap.put("errMsg",e.toString());
                    return modelMap;
                }
            }else {
                userService.updateUser(user);
                modelMap.put("success",true);
                modelMap.put("message","用户已存在，信息修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }


        return modelMap;
    }

}
