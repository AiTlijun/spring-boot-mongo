package com.example.springbootmongo.response;

import java.util.List;

public class TableModel<T> {

    private int code;
    private String msg;
    private int count;
    private List<T> data;

    public TableModel() {
    }

    public TableModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TableModel(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
