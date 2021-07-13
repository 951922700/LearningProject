package com.lyl.work.dao;

import com.lyl.work.BaseTest;
import com.lyl.work.entity.ChatWall;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ChatWallDaoTest extends BaseTest {
    @Autowired
    private ChatWallDao chatWallDao;

    @Test
    public void testQuery(){
        List<ChatWall> chatWalls=chatWallDao.queryAll();
        for (ChatWall chatWall:chatWalls)
            System.out.println(chatWall);
    }

    @Test
    public void testadd(){
       /* ChatWall chatWall=new ChatWall();
        chatWall.setChatId("2");
        chatWall.setContent("啊啊啊");
        chatWall.setCreateTime(new Date());
        chatWall.setImgNum(1);
        chatWall.setUserName("嘿嘿");
        chatWall.setUserImg("......");
        chatWallDao.createChatWall(chatWall);*/
       int num=chatWallDao.queryImgNum("2");
        System.out.println(num);
        /*ChatWall chatWall=new ChatWall();
        chatWall.setChatId("2");
        chatWall.setImgNum(2);
       chatWallDao.addImgNum(chatWall);*/
    }
}
