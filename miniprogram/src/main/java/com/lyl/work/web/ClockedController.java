package com.lyl.work.web;

import com.lyl.work.entity.Clocked;
import com.lyl.work.service.ClockedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/clocked")
public class ClockedController {

    @Autowired
    private ClockedService clockedService;

    //当前日期
    Calendar calendar = Calendar.getInstance();
    int now_year,now_month,now_day;
    //最近一次打卡时间存放
    Calendar checked_calendar = Calendar.getInstance();
    int checked_year,checked_month,checked_day;
    //是否打卡
    Boolean success = false;

    @RequestMapping("/update")
    @ResponseBody
    //传入用户openid
    private Map<String,Object> updateClocked(@RequestParam("openId") String openId) {
        Map<String,Object> modelMap=new HashMap<String,Object>();



        /**
         * 判断打卡新老用户
         */
        Clocked clocked = clockedService.queryClockedByid(openId);

        try {
            if (clocked != null) {            //老用户打卡

                success = dateCompare(clocked);
                if (success) {    //打卡成功
                    clockedService.updateClocked(openId);
                    modelMap.put("times", clocked.getClTimes() + 1);
                    System.out.println("用户" + openId + "打卡成功！"+"/n天数:"+(clocked.getClTimes() + 1));
                } else if (!success) { //打卡失败
                    System.out.println("用户" + openId + "打卡失败！"+"/n天数:"+ clocked.getClTimes() );
                    modelMap.put("times", clocked.getClTimes());
                }
            } else if (clocked == null) {       //新用户
                clockedService.insertNewClocked(openId);
                System.out.println("新用户异常”" + openId + "“打卡！");
                success = true;
                modelMap.put("times", 1);
            }
            modelMap.put("success", success);
        }catch(Exception e){
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }

    @RequestMapping("/times")
    @ResponseBody
    private Map<String,Object> getTimes(@RequestParam("openId") String openId ){

        Map<String,Object> modelMap=new HashMap<String,Object>();


        try {
            /**获得打卡天数,新用户是NULL*/
            Integer clTimes = clockedService.queryTimes(openId);

            if (clTimes != null) {        //老用户
                modelMap.put("times", clTimes);
                System.out.println("老用户”" + openId + "“查询天数：" + clTimes);
            } else if (clTimes == null)    //新用户插入
            {
                clockedService.insertNewOpenId(openId);
                System.out.println("新用户”" + openId + "“查询天数：0");
                System.out.println("新用户插入成功！");
                success = true;
                modelMap.put("times", 0);
            }
        }catch(Exception e){
            modelMap.put("errMsg",e.toString());    //信息
        }
        return modelMap;
    }


    /**老用户比较时间*/
    private boolean dateCompare(Clocked clocked){

        /**
         * 获得当前年月日
         */
        calendar.setTime(new Date());
        now_year = calendar.get(Calendar.YEAR);
        now_month = calendar.get(Calendar.MONTH)+1;
        now_day = calendar.get(Calendar.DATE);


        /**
         * 新用户注册的lastTime为NULL直接返回true
            允许打卡
         */
        if(clocked.getLastTime() == null){return true;}

        /**
         * 打卡时间
         */
        checked_calendar.setTime(clocked.getLastTime());
        checked_year = checked_calendar.get(Calendar.YEAR);
        checked_month = checked_calendar.get(Calendar.MONTH)+1;
        checked_day = checked_calendar.get(Calendar.DATE);


        //判断比较时间
        if(now_year>checked_year){
            System.out.println("year错误"+success);
            return true;
        } else if(now_month > checked_month){
            return true;
        }else if(now_day > checked_day){
            return true;
        }
            return false;
    }
}
