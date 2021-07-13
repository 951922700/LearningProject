package com.lyl.o2o.entity;

import java.util.Date;

public class Area {
    //如果用基本数据类型，当我们赋NULL的时候会默认为0，用引用数据类型就不会
    //ID
    private Integer areaId;
    //名称
    private String areaName;
    //权重
    private Integer priority;
    //创建时间
    private Date craeteTime;
    //更新时间
    private Date lastEditTime;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCraeteTime() {
        return craeteTime;
    }

    public void setCraeteTime(Date craeteTime) {
        this.craeteTime = craeteTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
