package com.example.springbootmongo.response;

import org.springframework.data.domain.Page;

public class BhResponseResult<T> {
    private int code;
    private String msg;
    private Page<T> page;

    public BhResponseResult() {
    }

    public BhResponseResult(int code, String msg, Page<T> page) {
        this.code = code;
        this.msg = msg;
        this.page = page;
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

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
