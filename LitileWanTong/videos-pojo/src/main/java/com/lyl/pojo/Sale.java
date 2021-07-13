package com.lyl.pojo;

import java.math.BigDecimal;

public class Sale {
    private Integer sid;

    /**
     * 折扣价
     */
    private BigDecimal dcprice;

    /**
     * 市场价
     */
    private BigDecimal ogprice;

    /**
     * 消费数
     */
    private Integer num;

    /**
     * 体验说明
     */
    private String explain;

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
     * 获取折扣价
     *
     * @return dcprice - 折扣价
     */
    public BigDecimal getDcprice() {
        return dcprice;
    }

    /**
     * 设置折扣价
     *
     * @param dcprice 折扣价
     */
    public void setDcprice(BigDecimal dcprice) {
        this.dcprice = dcprice;
    }

    /**
     * 获取市场价
     *
     * @return ogprice - 市场价
     */
    public BigDecimal getOgprice() {
        return ogprice;
    }

    /**
     * 设置市场价
     *
     * @param ogprice 市场价
     */
    public void setOgprice(BigDecimal ogprice) {
        this.ogprice = ogprice;
    }

    /**
     * 获取消费数
     *
     * @return num - 消费数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置消费数
     *
     * @param num 消费数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取体验说明
     *
     * @return explain - 体验说明
     */
    public String getExplain() {
        return explain;
    }

    /**
     * 设置体验说明
     *
     * @param explain 体验说明
     */
    public void setExplain(String explain) {
        this.explain = explain;
    }
}