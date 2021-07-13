package com.lyl.work.web;

import com.lyl.work.entity.ChatWall;
import com.lyl.work.entity.Comment;
import com.lyl.work.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 创建评论
     * @param comment
     * @return
     */
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> sendComment(Comment comment){
        Map<String,Object> modelMap=new HashMap<>();

        if(comment.getCommentId()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","commentId为空");
            return modelMap;
        }else if(comment.getCommentBelongChatId()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","所属chatId为空");
            return modelMap;
        }else if(comment.getCommentContent()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","commentContent为空");
            return modelMap;
        }else if(comment.getUserImg()==null||comment.getUserName()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","用户姓名或者头像url为空");
            return modelMap;
        }

        comment.setCreateTime(new Date());
        comment.setImgNum(0);//刚创建初始化为0
        try{
            commentService.createComment(comment);
            modelMap.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }

        return modelMap;
    }

    /**
     * 根据chatId查询出所有评论
     */
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryAllById(String chatId){
        Map<String,Object> modelMap=new HashMap<>();
        List<Comment>comments=new ArrayList<>();
        if(chatId==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","chatId为空");
            return modelMap;
        }

        try {
            comments= commentService.queryAllById(chatId);
            modelMap.put("data",comments);
            modelMap.put("success",true);
            modelMap.put("length",comments.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }

    /**
     * 评论上传图片，小程序每次上传一条
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,Object> uploadImage(HttpServletRequest request, @RequestParam("upload") MultipartFile upload, @RequestParam("commentId") String commentId){

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
        if(commentId==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有传commentID");
            return modelMap;
        }
        if(commentService.queryImgNum(commentId)==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","请检查commentId是否正确");
            return modelMap;
        }
        try {
            num = commentService.queryImgNum(commentId);
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }

        num=num+1;
        String fileName=commentId+"_"+num+".jpg";
        //封装
        Comment comment=new Comment();
        comment.setImgNum(num);
        comment.setCommentId(commentId);
        try {
            upload.transferTo(new File(path,fileName));
            //上传成功，数据库num要加1
            commentService.addImgNum(comment);
            modelMap.put("fileName",fileName);
            modelMap.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",true);
            modelMap.put("errMsg",e.toString());
            return  modelMap;
        }
        return modelMap;
    }
}
