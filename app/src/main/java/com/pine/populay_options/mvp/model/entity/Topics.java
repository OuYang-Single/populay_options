package com.pine.populay_options.mvp.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Topics implements Parcelable {
    private Integer id;

    private long createTime;

    private int userid;

    private Integer awesome;

    private long updateTime;

    private String content;

    private int islike;

    private String image;

    private String title;

    private  List<ImageInfo> imageinfoList;


    protected Topics(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        createTime = in.readLong();
        userid = in.readInt();
        if (in.readByte() == 0) {
            awesome = null;
        } else {
            awesome = in.readInt();
        }
        updateTime = in.readLong();
        content = in.readString();
        islike = in.readInt();
        image = in.readString();
        title = in.readString();
        imageinfoList = in.createTypedArrayList(ImageInfo.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeLong(createTime);
        dest.writeInt(userid);
        if (awesome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(awesome);
        }
        dest.writeLong(updateTime);
        dest.writeString(content);
        dest.writeInt(islike);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeTypedList(imageinfoList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Topics> CREATOR = new Creator<Topics>() {
        @Override
        public Topics createFromParcel(Parcel in) {
            return new Topics(in);
        }

        @Override
        public Topics[] newArray(int size) {
            return new Topics[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Integer getAwesome() {
        return awesome;
    }

    public void setAwesome(Integer awesome) {
        this.awesome = awesome;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageInfo> getImageinfoList() {
        return imageinfoList;
    }

    public void setImageinfoList(List<ImageInfo> imageinfoList) {
        this.imageinfoList = imageinfoList;
    }
}
