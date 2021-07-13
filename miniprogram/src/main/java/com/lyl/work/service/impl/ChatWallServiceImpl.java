package com.lyl.work.service.impl;

import com.lyl.work.dao.ChatWallDao;
import com.lyl.work.entity.ChatWall;
import com.lyl.work.service.ChatWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatWallServiceImpl implements ChatWallService {
    @Autowired
    private ChatWallDao chatWallDao;

    @Override
    public List<ChatWall> queryAll() {
        return chatWallDao.queryAll();
    }

    @Override
    public void createChatWall(ChatWall chatWall) {
        chatWallDao.createChatWall(chatWall);
    }

    @Override
    public Integer queryImgNum(String chatId) {
        return chatWallDao.queryImgNum(chatId);
    }

    @Override
    public void addImgNum(ChatWall chatWall) {
        chatWallDao.addImgNum(chatWall);
    }
}
