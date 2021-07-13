package com.lyl.o2o.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 一个章节和标题
 */
public class UnitTitle implements Serializable {
    private Integer uid;

    private String title;

    private List<Unit> units;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "UnitTitle{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", units=" + units +
                '}';
    }
}
