package com.lyl.work.web;

import com.lyl.work.entity.ChatWall;
import com.lyl.work.service.ChatWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/chat")
public class ChatWallController {
    @Autowired
    private ChatWallService chatWallService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = "/queryall",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryAll(){
        Map<String,Object> modelMap=new HashMap<>();
        List<ChatWall> chatWalls=new ArrayList<>();
        try{
            chatWalls=chatWallService.queryAll();
            modelMap.put("data",chatWalls);
            modelMap.put("length",chatWalls.size());
            modelMap.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }

    /**
     * 创建
     * @param chatWall
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> create(ChatWall chatWall){
        System.out.println(chatWall);
        Map<String,Object> modelMap=new HashMap<>();

        if(chatWall.getChatId()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","chatId为空");
            return modelMap;
        }else if(chatWall.getContent()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","content为空");
            return modelMap;
        }else if(chatWall.getIsNot()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","故事和话题判断依据(isNot)为空");//0为话题，1故事
            return modelMap;
        }
        else if(chatWall.getUserImg()==null||chatWall.getUserName()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","用户姓名或者头像url为空");
            return modelMap;
        }

        chatWall.setCreateTime(new Date());
        chatWall.setImgNum(0);//刚创建初始化为0
        try{
            chatWallService.createChatWall(chatWall);
            modelMap.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }

    /**
     * 聊天墙上传图片，小程序每次上传一条
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> uploadImage(HttpServletRequest request, @RequestParam("upload") MultipartFile upload,@RequestParam("chatId") String chatId){

        Map<String,Object> modelMap=new HashMap<>();

        //上传路径
        String path="C:/upload/image/";

        System.out.println(path);
        File file=new File(path);

        if(!file.exists()){
            //没有就创建该文件夹
            file.mkdirs();
        }
        int num= 0;
        //System.out.println(chatId);
        if(chatId==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有传chatID");
            return modelMap;
        }
        if(chatWallService.queryImgNum(chatId)==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请检查chatId是否正确");
            return modelMap;
        }
        try {
            num = chatWallService.queryImgNum(chatId);
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        num=num+1;
        String fileName=chatId+"_"+num+".jpg";
        //封装
        ChatWall chatWall=new ChatWall();
        chatWall.setImgNum(num);
        chatWall.setChatId(chatId);
        try {
            upload.transferTo(new File(path,fileName));
            //上传成功，数据库num要加1
            chatWallService.addImgNum(chatWall);
            modelMap.put("fileName",fileName);
            modelMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",true);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }
}
