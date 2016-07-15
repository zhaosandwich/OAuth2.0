package com.resource.common;

/**
 * Created by Sandwich on 2016/7/7.
 */
public class Result {
    private String statusCode;   //状态码
    private String msg;          //信息
    private Object data;        //数据

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
