package com.lyl.pojo;

import javax.persistence.*;

public class User {
    private String username;

    private String password;

    private String type;

    @Column(name = "icon_path")
    private String iconPath;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return icon_path
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}