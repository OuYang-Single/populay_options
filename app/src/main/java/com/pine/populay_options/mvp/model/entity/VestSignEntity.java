package com.pine.populay_options.mvp.model.entity;

public class VestSignEntity {
    private   String fieldCol;;
    private String  currversion;
    private     String    hul;
    private  String backgroundCol;
    private  int        advOn;
    private  String   advImg;
    private  String          advUrl;
    private  String     channelName;
    private  String           channelCo;
    private  int   sta;
    private  boolean   toolbar;

    public boolean isToolbar() {
        return toolbar;
    }

    public void setToolbar(boolean toolbar) {
        this.toolbar = toolbar;
    }

    public String getCurrversion() {
        return currversion;
    }

    public void setCurrversion(String currversion) {
        this.currversion = currversion;
    }

    public String getHul() {
        return hul;
    }

    public void setHul(String hul) {
        this.hul = hul;
    }

    public String getBackgroundCol() {
        return backgroundCol;
    }

    public void setBackgroundCol(String backgroundCol) {
        this.backgroundCol = backgroundCol;
    }

    public int getAdvOn() {
        return advOn;
    }

    public void setAdvOn(int advOn) {
        this.advOn = advOn;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCo() {
        return channelCo;
    }

    public void setChannelCo(String channelCo) {
        this.channelCo = channelCo;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public String getFieldCol() {
        return fieldCol;
    }

    public void setFieldCol(String fieldCol) {
        this.fieldCol = fieldCol;
    }
}
