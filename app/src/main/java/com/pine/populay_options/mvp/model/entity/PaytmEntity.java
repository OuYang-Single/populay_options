package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PaytmEntity implements Parcelable {
    private String textToken;
    private String orderId;
    private String mid;
    private double amount;

    protected PaytmEntity(Parcel in) {
        textToken = in.readString();
        orderId = in.readString();
        mid = in.readString();
        amount = in.readDouble();
    }

    public static final Creator<PaytmEntity> CREATOR = new Creator<PaytmEntity>() {
        @Override
        public PaytmEntity createFromParcel(Parcel in) {
            return new PaytmEntity(in);
        }

        @Override
        public PaytmEntity[] newArray(int size) {
            return new PaytmEntity[size];
        }
    };

    public String getTextToken() {
        return textToken;
    }

    public void setTextToken(String textToken) {
        this.textToken = textToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textToken);
        dest.writeString(orderId);
        dest.writeString(mid);
        dest.writeDouble(amount);
    }
}
