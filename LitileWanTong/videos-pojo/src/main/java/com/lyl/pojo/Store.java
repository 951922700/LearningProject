package com.lyl.pojo;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

public class Store {
    /**
     * 门店id
     */
    @Id
    private Integer sid;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 电话号码
     */
    private String phonenumber;

    /**
     * 图片数量
     */
    private Integer images;

    /**
     * 平均价格
     */
    private BigDecimal averprice;

    /**
     * 门店类型
     */
    private String type;

    /**
     * 评分
     */
    private Integer grade;

    /**
     * 已预约人数
     */
    private Integer applynum;

    /**
     * 功能
     */
    private String fun;

    /**
     * 宣传标语
     */
    private String slogan;

    /**
     * 优惠
     */
    private List<Sale> sales;

    /**
     * 套餐
     */
    private List<Set> sets;

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
     * 获取门店名称
     *
     * @return name - 门店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置门店名称
     *
     * @param name 门店名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
     * 获取图片数量
     *
     * @return images - 图片数量
     */
    public Integer getImages() {
        return images;
    }

    /**
     * 设置图片数量
     *
     * @param images 图片数量
     */
    public void setImages(Integer images) {
        this.images = images;
    }

    /**
     * 获取平均价格
     *
     * @return averprice - 平均价格
     */
    public BigDecimal getAverprice() {
        return averprice;
    }

    /**
     * 设置平均价格
     *
     * @param averprice 平均价格
     */
    public void setAverprice(BigDecimal averprice) {
        this.averprice = averprice;
    }

    /**
     * 获取门店类型
     *
     * @return type - 门店类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置门店类型
     *
     * @param type 门店类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取评分
     *
     * @return grade - 评分
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置评分
     *
     * @param grade 评分
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取已预约人数
     *
     * @return applynum - 已预约人数
     */
    public Integer getApplynum() {
        return applynum;
    }

    /**
     * 设置已预约人数
     *
     * @param applynum 已预约人数
     */
    public void setApplynum(Integer applynum) {
        this.applynum = applynum;
    }

    /**
     * 获取功能
     *
     * @return fun - 功能
     */
    public String getFun() {
        return fun;
    }

    /**
     * 设置功能
     *
     * @param fun 功能
     */
    public void setFun(String fun) {
        this.fun = fun;
    }

    /**
     * 获取宣传标语
     *
     * @return slogan - 宣传标语
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * 设置宣传标语
     *
     * @param slogan 宣传标语
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}