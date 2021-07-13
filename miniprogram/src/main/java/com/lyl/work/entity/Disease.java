package com.lyl.work.entity;

import java.util.List;

public class Disease {


    //id号主键
    private Integer dId;

    //大分块内容简介
    private String dTitle;

    //大分块中的小分块详细内容
    private List<Title_Disease>  titleDiseases;


    //在API返回小分块数量
    public int gettltle_num(){
        return titleDiseases.size();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public List<Title_Disease> getTitleDiseases() {
        return titleDiseases;
    }

    public void setTitleDiseases(List<Title_Disease> titleDiseases) {
        this.titleDiseases = titleDiseases;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "dId=" + dId +
                ", dTitle='" + dTitle + '\'' +
                ", titleDiseases=" + titleDiseases +
                '}';
    }
}
