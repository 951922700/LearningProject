package com.lyl.work.entity;

import java.io.Serializable;

public class SharingCase implements Serializable {
    private String id;

    private String title;

    private String pre;

    private String end;

    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SharingCase{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pre='" + pre + '\'' +
                ", end='" + end + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
