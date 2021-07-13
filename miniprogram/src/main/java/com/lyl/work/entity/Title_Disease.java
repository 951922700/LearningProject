package com.lyl.work.entity;

public class Title_Disease {

    //外键连接
//    private Integer  dcId;

    //大分块中小分块名称
    private String dcName;

    //大分块中小分块内容
    private String dcContent;

    //照片
    private String dcImg;

    //视频
    private String dcVideo;

//    public Integer getDcId() {
//        return dcId;
//    }
//
//    public void setDcId(Integer dcId) {
//        this.dcId = dcId;
//    }

    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    public String getDcContent() {
        return dcContent;
    }

    public void setDcContent(String dcContent) {
        this.dcContent = dcContent;
    }

    public String getDcImg() {
        return dcImg;
    }

    public void setDcImg(String dcImg) {
        this.dcImg = dcImg;
    }

    public String getDcVideo() {
        return dcVideo;
    }

    public void setDcVideo(String dcVideo) {
        this.dcVideo = dcVideo;
    }

    @Override
    public String toString() {
        return "Title_Disease{" +
                "dcName='" + dcName + '\'' +
                ", dcContent='" + dcContent + '\'' +
                ", dcImg='" + dcImg + '\'' +
                ", dcVideo='" + dcVideo + '\'' +
                '}';
    }
}
