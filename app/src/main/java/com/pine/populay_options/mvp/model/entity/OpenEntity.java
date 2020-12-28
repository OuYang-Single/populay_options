package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class OpenEntity implements Parcelable {
    private String sign;
    private String host;
    public OpenEntity(){}
    protected OpenEntity(Parcel in) {
        sign = in.readString();
        host = in.readString();
    }

    public static final Creator<OpenEntity> CREATOR = new Creator<OpenEntity>() {
        @Override
        public OpenEntity createFromParcel(Parcel in) {
            return new OpenEntity(in);
        }

        @Override
        public OpenEntity[] newArray(int size) {
            return new OpenEntity[size];
        }
    };

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sign);
        dest.writeString(host);
    }
}
