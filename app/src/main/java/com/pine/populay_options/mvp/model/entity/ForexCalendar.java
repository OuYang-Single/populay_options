package com.pine.populay_options.mvp.model.entity;

public class ForexCalendar {
    private String time;
    private String Currency;
    private String Event;

    public ForexCalendar(String time,String Currency,String Event){
        this.time=time;
        this.Currency=Currency;
        this.Event=Event;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}