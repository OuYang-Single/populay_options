package com.pine.populay_options.mvp.model.entity;

public class BranchEvent<T> {
    public static final String ShowTitleBarEVent="showTitleBar";
    private String EventName;
    private T Data;
    public BranchEvent(String EventName,T Data) {

    }
    public BranchEvent(String EventName) {

    }
    public BranchEvent() {

    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

}
