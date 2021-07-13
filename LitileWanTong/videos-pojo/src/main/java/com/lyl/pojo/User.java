package com.lyl.pojo;

import javax.persistence.Column;
import java.util.List;

public class User {
    private String uid;

    /**
     * 医生id
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 头像
     */
    private String icon;

    /**
     * 城市
     */
    private String city;

    private Babyinfo babyinfo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Babyinfo getBabyinfo() {
        return babyinfo;
    }

    public void setBabyinfo(Babyinfo babyinfo) {
        this.babyinfo = babyinfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}