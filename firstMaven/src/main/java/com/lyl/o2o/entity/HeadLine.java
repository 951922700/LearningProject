package com.lyl.o2o.entity;

import java.util.Date;

public class HeadLine {
    private Long lineID;
    private String lineName;
    private String lineLinlk;
    private String lineImg;
    private Integer priority;
    //0.不可用,1.可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;

    public Long getLineID() {
        return lineID;
    }

    public void setLineID(Long lineID) {
        this.lineID = lineID;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLinlk() {
        return lineLinlk;
    }

    public void setLineLinlk(String lineLinlk) {
        this.lineLinlk = lineLinlk;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
