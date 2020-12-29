package com.pine.populay_options.mvp.model.entity;

import com.pine.populay_options.app.ResponseErrorListenerImpl;

public class ErrorEntity {
    public ResponseErrorListenerImpl.EVENT_KEY EventName ;
    public String messing;
    public ErrorEntity(ResponseErrorListenerImpl.EVENT_KEY EventName,String messing){
        this.EventName=EventName;
        this.messing=messing;
    }
}
