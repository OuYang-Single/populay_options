package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.pine.populay_options.mvp.model.wigth.chatkit.commons.models.IMessage;
import com.pine.populay_options.mvp.model.wigth.chatkit.commons.models.IUser;
import com.pine.populay_options.mvp.model.wigth.chatkit.commons.models.MessageContentType;

import java.util.Date;

public class Message implements MessageContentType, Parcelable {
  private   String  Id;
  private   String  Text;
  private   Users  User;
  private   Date  CreatedAt;
  public Message(){}

    protected Message(Parcel in) {
        Id = in.readString();
        Text = in.readString();
        User = in.readParcelable(Users.class.getClassLoader());
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public void setText(String text) {
        Text = text;
    }

    public void setUser(Users user) {
        User = user;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public String getText() {
        return Text;
    }

    @Override
    public IUser getUser() {
        return User;
    }

    @Override
    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setId(String  Id){
        this.Id=Id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Text);
        dest.writeParcelable(User, flags);
    }
}
