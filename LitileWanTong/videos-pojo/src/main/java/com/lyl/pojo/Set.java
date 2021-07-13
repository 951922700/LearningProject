package com.lyl.pojo;

import java.math.BigDecimal;

public class Set {
    private Integer sid;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 套餐描述
     */
    private String descripe;

    /**
     * 套餐市场价
     */
    private BigDecimal ogprice;

    /**
     * 套餐折扣价
     */
    private BigDecimal dcprice;

    /**
     * @return sid
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取图片路径
     *
     * @return image - 图片路径
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片路径
     *
     * @param image 图片路径
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取套餐描述
     *
     * @return descripe - 套餐描述
     */
    public String getDescripe() {
        return descripe;
    }

    /**
     * 设置套餐描述
     *
     * @param descripe 套餐描述
     */
    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    /**
     * 获取套餐市场价
     *
     * @return ogprice - 套餐市场价
     */
    public BigDecimal getOgprice() {
        return ogprice;
    }

    /**
     * 设置套餐市场价
     *
     * @param ogprice 套餐市场价
     */
    public void setOgprice(BigDecimal ogprice) {
        this.ogprice = ogprice;
    }

    /**
     * 获取套餐折扣价
     *
     * @return dcprice - 套餐折扣价
     */
    public BigDecimal getDcprice() {
        return dcprice;
    }

    /**
     * 设置套餐折扣价
     *
     * @param dcprice 套餐折扣价
     */
    public void setDcprice(BigDecimal dcprice) {
        this.dcprice = dcprice;
    }
}