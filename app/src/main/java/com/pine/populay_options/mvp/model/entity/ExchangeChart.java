package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ExchangeChart implements Parcelable {
    private  String  d;
    private  String  o;
    private  String  l;
    private  String  h;
    private  String  c;

    protected ExchangeChart(Parcel in) {
        d = in.readString();
        o = in.readString();
        l = in.readString();
        h = in.readString();
        c = in.readString();
    }

    public static final Creator<ExchangeChart> CREATOR = new Creator<ExchangeChart>() {
        @Override
        public ExchangeChart createFromParcel(Parcel in) {
            return new ExchangeChart(in);
        }

        @Override
        public ExchangeChart[] newArray(int size) {
            return new ExchangeChart[size];
        }
    };

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(d);
        dest.writeString(o);
        dest.writeString(l);
        dest.writeString(h);
        dest.writeString(c);
    }
}
