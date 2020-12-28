package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Request<T> implements Parcelable {
    private T data;
    private int code;
    private String timestamp;
    private String token;
    private String msg;
    private String dts;

    public Request(){}
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected Request(Parcel in) {
        code = in.readInt();
        timestamp = in.readString();
        token = in.readString();
        msg = in.readString();
        dts = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(timestamp);
        dest.writeString(token);
        dest.writeString(msg);
        dest.writeString(dts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    public String getDts() {
        return dts;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }
}
