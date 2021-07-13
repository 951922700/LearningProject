package com.lyl.work.entity;

//菜内容详细资料
public class DishContent {

//    //id与menu的ID关联
//    private int id;
    //材料
    private String material;
    //调料
    private String flavouring;
    //做法
    private  String doing;
    //功效
    private String effect;
    //来源
    private String from;
    //作者
    private String writer;
    //丛书名
    private String seriesTitle;
    //出版日期
    private String date;



    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFlavouring() {
        return flavouring;
    }

    public void setFlavouring(String flavouring) {
        this.flavouring = flavouring;
    }

    public String getDoing() {
        return doing;
    }

    public void setDoing(String doing) {
        this.doing = doing;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DishContent{" +
                ", material='" + material + '\'' +
                ", flavouring='" + flavouring + '\'' +
                ", doing='" + doing + '\'' +
                ", effect='" + effect + '\'' +
                ", from='" + from + '\'' +
                ", writer='" + writer + '\'' +
                ", seriesTitle='" + seriesTitle + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
