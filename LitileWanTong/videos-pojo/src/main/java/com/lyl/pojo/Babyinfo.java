package com.lyl.pojo;

import java.util.Date;

public class Babyinfo {
    private String uid;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 宝宝信息插入时间
     */
    private Date date;

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取身高
     *
     * @return height - 身高
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置身高
     *
     * @param height 身高
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * 获取体重
     *
     * @return weight - 体重
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置体重
     *
     * @param weight 体重
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取兴趣
     *
     * @return interest - 兴趣
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 设置兴趣
     *
     * @param interest 兴趣
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}