package com.lyl.work.entity;

import java.util.List;

public class Sport {

    //运动阶段
    private String stageId;
    //阶段名
    private String stageName;
    //阶段
    private List<Stage_Sport> stageSportList;



    public List<Stage_Sport> getStageSportList() {
        return stageSportList;
    }

    public void setStageSportList(List<Stage_Sport> stageSportList) {
        this.stageSportList = stageSportList;
    }


    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public int getSportNum(){
        return stageSportList.size();
    }

    @Override
    public String toString() {
        return "Sport{" +
                "stageId=" + stageId +
                ", stageName='" + stageName + '\'' +
                ", stageSportList=" + stageSportList +
                '}';
    }
}
