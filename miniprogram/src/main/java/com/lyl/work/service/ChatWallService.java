package com.lyl.work.service;

import com.lyl.work.entity.ChatWall;

import java.util.List;

public interface ChatWallService {
    List<ChatWall> queryAll();

    void createChatWall(ChatWall chatWall);

    Integer queryImgNum(String chatId);


    void addImgNum(ChatWall chatWall);
}
