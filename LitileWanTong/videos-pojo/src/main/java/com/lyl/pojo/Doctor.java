package com.lyl.pojo;

import javax.persistence.Id;

public class Doctor {
    /**
     * 医生id
     */
    @Id
    private Integer id;

    /**
     * 门店id
     */
    private Integer sid;

    /**
     * 名字
     */
    private String name;

    /**
     * 职位
     */
    private String positon;

    /**
     * 擅长
     */
    private String special;

    /**
     * 电话号码
     */
    private String phonenumber;

    /**
     * 头像
     */
    private String icon;

    /**
     * 证书
     */
    private String certificate;

    /**
     * 粉丝数量
     */
    private String fans;

    /**
     * 咨询量
     */
    private String consult;

    /**
     * 获取医生id
     *
     * @return id - 医生id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置医生id
     *
     * @param id 医生id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取门店id
     *
     * @return sid - 门店id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置门店id
     *
     * @param sid 门店id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取职位
     *
     * @return positon - 职位
     */
    public String getPositon() {
        return positon;
    }

    /**
     * 设置职位
     *
     * @param positon 职位
     */
    public void setPositon(String positon) {
        this.positon = positon;
    }

    /**
     * 获取擅长
     *
     * @return special - 擅长
     */
    public String getSpecial() {
        return special;
    }

    /**
     * 设置擅长
     *
     * @param special 擅长
     */
    public void setSpecial(String special) {
        this.special = special;
    }

    /**
     * 获取电话号码
     *
     * @return phonenumber - 电话号码
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 设置电话号码
     *
     * @param phonenumber 电话号码
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * 获取头像
     *
     * @return icon - 头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置头像
     *
     * @param icon 头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取证书
     *
     * @return certificate - 证书
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * 设置证书
     *
     * @param certificate 证书
     */
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    /**
     * 获取粉丝数量
     *
     * @return fans - 粉丝数量
     */
    public String getFans() {
        return fans;
    }

    /**
     * 设置粉丝数量
     *
     * @param fans 粉丝数量
     */
    public void setFans(String fans) {
        this.fans = fans;
    }

    /**
     * 获取咨询量
     *
     * @return consult - 咨询量
     */
    public String getConsult() {
        return consult;
    }

    /**
     * 设置咨询量
     *
     * @param consult 咨询量
     */
    public void setConsult(String consult) {
        this.consult = consult;
    }
}