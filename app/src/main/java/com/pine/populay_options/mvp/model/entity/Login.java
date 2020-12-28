package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {
    private String token1;
    private  String token2;
    private String url;
    public Login(){}

    protected Login(Parcel in) {
        token1 = in.readString();
        token2 = in.readString();
        url = in.readString();
    }

    public String getToken1() {
        return token1;
    }

    public void setToken1(String token1) {
        this.token1 = token1;
    }

    public String getToken2() {
        return token2;
    }

    public void setToken2(String token2) {
        this.token2 = token2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token1);
        dest.writeString(token2);
        dest.writeString(url);
    }
}
