package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TradersEntity implements Parcelable {
    private int image;
    private String article;
    private String TradersName;
    public  TradersEntity(){}
    public  TradersEntity(int image,String TradersName,String article){
        this.image=image;
        this.TradersName=TradersName;
        this.article=article;
    }
    protected TradersEntity(Parcel in) {
        image = in.readInt();
        article = in.readString();
        TradersName = in.readString();
    }

    public static final Creator<TradersEntity> CREATOR = new Creator<TradersEntity>() {
        @Override
        public TradersEntity createFromParcel(Parcel in) {
            return new TradersEntity(in);
        }

        @Override
        public TradersEntity[] newArray(int size) {
            return new TradersEntity[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTradersName() {
        return TradersName;
    }

    public void setTradersName(String tradersName) {
        TradersName = tradersName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(article);
        dest.writeString(TradersName);
    }
}
