package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Videos implements Parcelable {
    private String VideosData;
    private String VideosName;
    private String Path;
    public Videos(){

    }
    protected Videos(Parcel in) {
        VideosData = in.readString();
        VideosName = in.readString();
        Path = in.readString();
    }

    public static final Creator<Videos> CREATOR = new Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel in) {
            return new Videos(in);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };

    public String getVideosData() {
        return VideosData;
    }

    public void setVideosData(String videosData) {
        VideosData = videosData;
    }

    public String getVideosName() {
        return VideosName;
    }

    public void setVideosName(String videosName) {
        VideosName = videosName;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(VideosData);
        dest.writeString(VideosName);
        dest.writeString(Path);
    }
}
