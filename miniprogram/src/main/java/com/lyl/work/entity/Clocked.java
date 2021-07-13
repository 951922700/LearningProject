package com.lyl.work.entity;

import java.util.Date;

public class Clocked {

    //用户，主键约束
    private String openId;

    //最后打卡时间
    private Date lastTime;


    //用户创建时间
    private Date createTime;

    //用户打卡次数
    private int clTimes;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getClTimes() {
        return clTimes;
    }

    public void setClTimes(int clTimes) {
        this.clTimes = clTimes;
    }


    @Override
    public String toString() {
        return "Clocked{" +
                "openId='" + openId + '\'' +
                ", lastTime=" + lastTime +
                ", createTime=" + createTime +
                ", clTimes=" + clTimes +
                '}';
    }
}
