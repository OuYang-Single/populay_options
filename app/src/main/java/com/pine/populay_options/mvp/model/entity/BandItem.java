package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class BandItem<T ,P>  implements Parcelable {
    public static final int HEAD=0;
    public static final int OPTION=1;
    public static final int CONTENT=2;
    public static final int Popular=3;
    private int mType;
    private T Data;
    private P Adapter;
    private String Title;

    public BandItem(){}

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public P getAdapter() {
        return Adapter;
    }

    public void setAdapter(P adapter) {
        Adapter = adapter;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    protected BandItem(Parcel in) {
        mType = in.readInt();
        Title = in.readString();
    }

    public static final Creator<BandItem> CREATOR = new Creator<BandItem>() {
        @Override
        public BandItem createFromParcel(Parcel in) {
            return new BandItem(in);
        }

        @Override
        public BandItem[] newArray(int size) {
            return new BandItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mType);
        parcel.writeString(Title);
    }
}
