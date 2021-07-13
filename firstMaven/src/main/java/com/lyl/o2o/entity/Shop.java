package com.lyl.o2o.entity;

import java.util.Date;

public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //-1.不可用0.审核中1.可用
    private Integer enableStatus;
    private String advice;
    private Area area;
    private PersonInfo owner;
    private ShopCategory shopCategory;
}
