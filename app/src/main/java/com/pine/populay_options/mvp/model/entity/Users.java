package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.pine.populay_options.mvp.model.wigth.chatkit.commons.models.IUser;

public class Users implements IUser, Parcelable {
    private String Id;
    private String Name;
    private String Avatar;
    private int Avatars;
    public Users(){}
    protected Users(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Avatar = in.readString();
        Avatars = in.readInt();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setAvatars(int avatars) {
        Avatars = avatars;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getAvatar() {
        return Avatar;
    }

    @Override
    public int getAvatars() {
        return Avatars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Name);
        dest.writeString(Avatar);
        dest.writeInt(Avatars);
    }
}
