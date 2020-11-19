package com.pine.populay_options.mvp.model.entity;

public class AliyunRequest<T> {
    private T Obj;
    private int Code;
    private String Msg;

    public T getObj() {
        return Obj;
    }

    public void setObj(T obj) {
        Obj = obj;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

}
