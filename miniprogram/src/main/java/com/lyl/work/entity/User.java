package com.lyl.work.entity;

import java.util.Date;

public class User {

    //小程序的openId
    private String openId;

    //用户头像
    private String userImg;

    //用户名字
    private String userName;

    //创建时间
    private Date createTime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId=" + openId +
                ", userImg='" + userImg + '\'' +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
