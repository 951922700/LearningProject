package com.lyl.work.entity;

//DishContent为菜品介绍
public class Menu extends DishContent{

    //ID号
    private Integer mId;

    //菜名
    private String DishName;

    //菜品照片数量
    private Integer imgNum;



    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }


    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mId=" + mId +
                ", DishName='" + DishName + '\'' +
                ", imgNum=" + imgNum +
                ",DishContent"+super.toString()+
                '}';
    }
}
