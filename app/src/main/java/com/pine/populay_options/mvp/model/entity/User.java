package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Collection;
import java.util.Date;
@Entity
public class User implements Parcelable {
    @Id
    private  Long id;
    private  String username;
    private  String password;
    private  String avatar;
    private  String email;
    private  String phone;
    private  String dept;
    private  String job;
    @Transient
    private Collection<String> roles;
    private  boolean enabled;
    private String createTime;
    private  Date lastPasswordResetDate;


    protected User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        username = in.readString();
        password = in.readString();
        avatar = in.readString();
        email = in.readString();
        phone = in.readString();
        dept = in.readString();
        job = in.readString();
        enabled = in.readByte() != 0;
        createTime = in.readString();
    }

    @Generated(hash = 302930083)
    public User(Long id, String username, String password, String avatar,
            String email, String phone, String dept, String job, boolean enabled,
            String createTime, Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.job = job;
        this.enabled = enabled;
        this.createTime = createTime;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(avatar);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(dept);
        dest.writeString(job);
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeString(createTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getLastPasswordResetDate() {
        return this.lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
