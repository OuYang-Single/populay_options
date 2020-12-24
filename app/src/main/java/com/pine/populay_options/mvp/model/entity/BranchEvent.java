package com.pine.populay_options.mvp.model.entity;

public class BranchEvent<T> {
    public static final String CALLBACKMETHOD="callbackMethod";
    public static final String NAME="name";
    private EVENT_KEY EventName;
    private T Data;
    public static enum EVENT_KEY {
        ShowTitleBarEVent("showTitleBar"),
        takePortraitPicture("takePortraitPicture");

        private String value;

        private EVENT_KEY(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
    public BranchEvent(EVENT_KEY EventName,T Data) {
      this.EventName=EventName;
      this.Data=Data;
    }
    public BranchEvent(EVENT_KEY EventName) {
        this.EventName=EventName;
    }
    public BranchEvent() {

    }

    public EVENT_KEY getEventName() {
        return EventName;
    }

    public void setEventName(EVENT_KEY eventName) {
        EventName = eventName;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

}
