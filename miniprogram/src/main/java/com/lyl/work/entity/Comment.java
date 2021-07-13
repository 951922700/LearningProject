package com.lyl.work.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //评论id
    private String commentId;
    //评论所属文章id
    private String commentBelongChatId;

    private String userName;

    private String userImg;

    private String commentContent;

    private Integer imgNum;

    private Date createTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    public String getCommentBelongChatId() {
        return commentBelongChatId;
    }

    public void setCommentBelongChatId(String commentBelongChatId) {
        this.commentBelongChatId = commentBelongChatId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId='" + commentId + '\'' +
                ", commentBelongChatId='" + commentBelongChatId + '\'' +
                ", userName='" + userName + '\'' +
                ", userImg='" + userImg + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", imgNum=" + imgNum +
                ", createTime=" + createTime +
                '}';
    }
}
