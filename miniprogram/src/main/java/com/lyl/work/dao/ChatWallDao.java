package com.lyl.work.dao;

import com.lyl.work.entity.ChatWall;

import java.util.List;

public interface ChatWallDao {
    /**
     * 查询全部聊天墙内容
     * @return
     */
    List<ChatWall> queryAll();

    /**
     * 创建一个聊天
     */
    void createChatWall(ChatWall chatWall);

    /**
     * 根据chatId 查图片数量
     */
    Integer queryImgNum(String chatId);

    /**
     * 根据chatId 更新图片数量
     */
    void addImgNum(ChatWall chatWall);
}
