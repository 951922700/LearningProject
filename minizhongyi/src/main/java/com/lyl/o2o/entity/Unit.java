package com.lyl.o2o.entity;

import java.io.Serializable;

/**
 * 某个章节某一页的内容
 */
public class Unit implements Serializable {
    private Integer cid;//主键，收藏

    private Integer uid;//章节，非主键

    private String content;//某页的内容

    private String title;//虽然重复但还是需要

    private String imgUrl;//某页内容的巧记部分

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
