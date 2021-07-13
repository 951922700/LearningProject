package com.lyl.work.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 除了聊天墙的的数据可以用，话题讨论的内容或者（故事）也行
 */
public class ChatWall implements Serializable {
    //聊天墙内容id
    private String chatId;
    //作者
    private String userName;
    //头像url
    private String userImg;
    //聊天内容
    private String content;
    //图片数量
    private Integer imgNum;
    //话题
    private String title;

    private String isNot;

    private Date createTime;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsNot() {
        return isNot;
    }

    public void setIsNot(String isNot) {
        this.isNot = isNot;
    }

    @Override
    public String toString() {
        return "ChatWall{" +
                "chatId='" + chatId + '\'' +
                ", userName='" + userName + '\'' +
                ", userImg='" + userImg + '\'' +
                ", content='" + content + '\'' +
                ", imgNum=" + imgNum +
                ", title='" + title + '\'' +
                ", isNot='" + isNot + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
