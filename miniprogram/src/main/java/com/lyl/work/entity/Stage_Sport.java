package com.lyl.work.entity;

public class Stage_Sport {

    //外键阶段ID
   // private String Id;

    //运动名
    private String sportName;

    //运动内容
    private String sportContent;

    //照片路径
    private String Img;

    //照片路径
    private String Img2;

    //视频
    private String video;


   /* public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }*/

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportContent() {
        return sportContent;
    }

    public void setSportContent(String sportContent) {
        this.sportContent = sportContent;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getImg2() {
        return Img2;
    }

    public void setImg2(String img2) {
        Img2 = img2;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    @Override
    public String toString() {
        return "Stage_Sport{" +
                "sportName='" + sportName + '\'' +
                ", sportContent='" + sportContent + '\'' +
                ", Img='" + Img + '\'' +
                ", Img2='" + Img2 + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
