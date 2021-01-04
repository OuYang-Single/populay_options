package com.pine.populay_options.mvp.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Topics implements Parcelable {
    private Integer id;

    private long createTime;

    private Integer userid;

    private Integer awesome;

    private long updateTime;

    private String content;

    private String image;

    private String title;

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
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

    protected Topics(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        createTime = in.readLong();
        if (in.readByte() == 0) {
            userid = null;
        } else {
            userid = in.readInt();
        }
        if (in.readByte() == 0) {
            awesome = null;
        } else {
            awesome = in.readInt();
        }
        updateTime = in.readLong();
        content = in.readString();
        image = in.readString();
        title = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
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
        if (userid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userid);
        }
        if (awesome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(awesome);
        }
        dest.writeLong(updateTime);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeString(title);
    }
}
