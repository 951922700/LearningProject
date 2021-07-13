package com.lyl.pojo.vo;

import com.lyl.pojo.Store;

import java.util.List;

public class StoreVo extends Store {
    private List<String> path;

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
