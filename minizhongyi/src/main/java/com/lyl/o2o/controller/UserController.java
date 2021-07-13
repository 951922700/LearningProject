package com.lyl.o2o.controller;

import com.lyl.o2o.entity.Question;
import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.User;
import com.lyl.o2o.service.QuestionService;
import com.lyl.o2o.service.UnitService;
import com.lyl.o2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UnitService unitService;
    /**
     * 注册,传账号密码
     * @param user
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regist(User user){
        Map<String,Object> modelMap=new HashMap<>();
        if(user.getAccount()==null||user.getPassword()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","账号或者密码为空");
            return modelMap;
        }
        try{
            if(userService.selectAccount(user)!=null){
                //如果该账号已存在
                modelMap.put("success",false);
                modelMap.put("errMsg","账号已存在");
                return modelMap;
            }
            user.setCreateTime(new Date());
            user.setUsername("小中医");
            userService.createUser(user);
            modelMap.put("success",true);
            modelMap.put("errMsg","用户注册成功");
        }catch(Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(User user){
        Map<String,Object> modelMap=new HashMap<>();
        if(user.getAccount()==null||user.getPassword()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","账号或者密码为空");
            return modelMap;
        }
        try{
            User user1 =userService.selectByAccount(user);
            if(user1==null){
                //没有这个用户
                modelMap.put("success",false);
                modelMap.put("errMsg","不存在该账户或者密码错误");
                return modelMap;
            }else{
                //存在该用户
                modelMap.put("success",true);
                modelMap.put("data",user1);
                modelMap.put("errMsg","登录成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }

    /**
     * 传题目或者学习内容的cid进来，用户的account，
     * @param user todo 接口 取消收藏？ 根据账户返回所有收藏的内容
     * @return
     */
    @RequestMapping(value = "/collect",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> collect(User user){
        Map<String,Object> modelMap=new HashMap<>();

        //先判断传值情况
        if(user.getAccount()==null&&(user.getQuestionCollect()==null||user.getUnitCollect()==null)){
            //如果账号为空或者qc和uc都为空那就缺少了
            modelMap.put("success",false);
            modelMap.put("errMsg","account为空或者qc和uc为空");
            return modelMap;
        }

        //发送收藏问题请求
        if(user.getQuestionCollect()!=null){

            //1.首先查询该账户信息得到questionCollect
            try {
                User u=userService.selectAccount(user);
                StringBuffer qc= new StringBuffer(u.getQuestionCollect());
                //将cid组合到qc后面
                qc.append(user.getQuestionCollect());
                User temp=new User();
                temp.setQuestionCollect(qc.toString());
                temp.setAccount(user.getAccount());
                userService.updateQuestionCollect(temp);
                modelMap.put("success",true);
                modelMap.put("errMsg","收藏成功");
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }

        //发送收藏学习内容请求
        if (user.getUnitCollect() != null) {

            //1.首先查询该账户信息得到unitCollect
            try {
                User u=userService.selectAccount(user);
                StringBuffer uc= new StringBuffer(u.getUnitCollect());
                //将cid组合到qc后面,-->分割
                uc.append("-->"+user.getUnitCollect());
                User temp=new User();
                temp.setUnitCollect(uc.toString());
                temp.setAccount(user.getAccount());
                userService.updateUnitCollect(temp);
                modelMap.put("success",true);
                modelMap.put("errMsg","收藏成功");
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }
        return modelMap;
    }

    /**
     * 传account得到收藏内容
     * @param user
     * @return
     */
    @RequestMapping(value=("/selectc"),method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> selectCollect(User user){
        Map<String,Object> modelMap=new HashMap<>();

        List<Question> questions=new ArrayList<Question>();
        List<Unit> units=new ArrayList<Unit>();

        if(user.getAccount()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","account为空");
            return  modelMap;
        }


        try {
            //1.获取到user的qc和uc
            User u = userService.selectAccount(user);
            String qc=u.getQuestionCollect();
            String uc=u.getUnitCollect();
            String qcs[]=qc.split("-->");
            String ucs[]=uc.split("-->");
            //2.for查询对应内容并添加到list
            for(int i=0;i<qcs.length;i++){
                Question q=new Question();
                q.setCid(Integer.parseInt(qcs[i]));
                Question question = questionService.queryByCid(q);
                questions.add(question);
            }

            for(int i=0;i<ucs.length;i++){
                Unit unit=new Unit();
                unit.setCid(Integer.parseInt(ucs[i]));
                Unit unit1 = unitService.queryUnitByCid(unit);
                units.add(unit1);
            }

            modelMap.put("questions",questions);
            modelMap.put("units",units);
            modelMap.put("success",true);

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return  modelMap;
        }

        return modelMap;
    }

    /**
     * 更新密码
     */
    @RequestMapping(value = "/updatep",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updatePassword(User user,@RequestParam("newp") String newPassword){
        Map<String,Object> modelMap=new HashMap<>();
        if(user.getAccount()==null||user.getPassword()==null||newPassword==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","account,password,newp三个可能为空");
            return modelMap;
        }

        try {
            User u = userService.selectByAccount(user);
            if(u==null){
                modelMap.put("success",false);
                modelMap.put("errMsg","账号或者密码错误");
            }else{
                User temp=new User();
                temp.setAccount(user.getAccount());
                temp.setPassword(newPassword);
                userService.updatePassword(temp);
                modelMap.put("success",true);
                modelMap.put("errMsg","修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return  modelMap;
        }
        return modelMap;
    }
}
