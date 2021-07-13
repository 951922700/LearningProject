package com.lyl.exception;

/**
 * 自定义异常类
 */
public class SysException extends Exception {
    //存储提示信息
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SysException(String message) {
        this.msg = message;
    }
}
