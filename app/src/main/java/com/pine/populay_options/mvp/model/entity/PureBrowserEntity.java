package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PureBrowserEntity implements Parcelable {
   private String title;// 标题
   private String url;// 加载的地址
   private boolean hasTitleBar;//  是否显示标题栏
   private boolean rewriteTitle;//  是否通过加载的Web重写标题
   private String stateBarTextColor;//  状态栏字体颜色 black|white
   private String titleTextColor;//  标题字体颜色
   private String titleColor;//  状态栏和标题背景色
   private String postData;//  webView post方法时会用到
   private boolean webBack;//  web回退(点击返回键webview可以回退就回退，无法回退的时候关闭该页面)|false(点击返回键关闭该页面) 直接关闭页面
    public  PureBrowserEntity(){}

    protected PureBrowserEntity(Parcel in) {
        title = in.readString();
        url = in.readString();
        hasTitleBar = in.readByte() != 0;
        rewriteTitle = in.readByte() != 0;
        stateBarTextColor = in.readString();
        titleTextColor = in.readString();
        titleColor = in.readString();
        postData = in.readString();
        webBack = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeByte((byte) (hasTitleBar ? 1 : 0));
        dest.writeByte((byte) (rewriteTitle ? 1 : 0));
        dest.writeString(stateBarTextColor);
        dest.writeString(titleTextColor);
        dest.writeString(titleColor);
        dest.writeString(postData);
        dest.writeByte((byte) (webBack ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PureBrowserEntity> CREATOR = new Creator<PureBrowserEntity>() {
        @Override
        public PureBrowserEntity createFromParcel(Parcel in) {
            return new PureBrowserEntity(in);
        }

        @Override
        public PureBrowserEntity[] newArray(int size) {
            return new PureBrowserEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHasTitleBar() {
        return hasTitleBar;
    }

    public void setHasTitleBar(boolean hasTitleBar) {
        this.hasTitleBar = hasTitleBar;
    }

    public boolean isRewriteTitle() {
        return rewriteTitle;
    }

    public void setRewriteTitle(boolean rewriteTitle) {
        this.rewriteTitle = rewriteTitle;
    }

    public String getStateBarTextColor() {
        return stateBarTextColor;
    }

    public void setStateBarTextColor(String stateBarTextColor) {
        this.stateBarTextColor = stateBarTextColor;
    }

    public String getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(String titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public boolean isWebBack() {
        return webBack;
    }

    public void setWebBack(boolean webBack) {
        this.webBack = webBack;
    }
}
