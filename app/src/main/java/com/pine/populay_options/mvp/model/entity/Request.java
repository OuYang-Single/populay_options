package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Request<T> implements Parcelable {
    private T data;
    private int status;
    private String timestamp;
    private String token;
    private String message;

    public T getUser() {
        return data;
    }

    public void setUser(T user) {
        this.data = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected Request(Parcel in) {
        status = in.readInt();
        timestamp = in.readString();
        token = in.readString();
        message = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(status);
        parcel.writeString(timestamp);
        parcel.writeString(token);
        parcel.writeString(message);
    }
}
