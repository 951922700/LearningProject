package com.lyl.o2o.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Integer id;//主键，自增

    private String account;//账号

    private String username;//昵称

    private String password;//密码

    private String questionCollect;//收藏id

    private String unitCollect;//页内容收藏

    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestionCollect() {
        return questionCollect;
    }

    public void setQuestionCollect(String questionCollect) {
        this.questionCollect = questionCollect;
    }

    public String getUnitCollect() {
        return unitCollect;
    }

    public void setUnitCollect(String unitCollect) {
        this.unitCollect = unitCollect;
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
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", questionCollect='" + questionCollect + '\'' +
                ", unitCollect='" + unitCollect + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
