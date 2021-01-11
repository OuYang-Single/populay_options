package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;


public class AdEntity implements Parcelable {
    private long Id;
    private long AimsId;
    private int AdType;
    private int JumpType;
    private String ImageName;
    private String ImageAddress;
    private String WebsiteAddress;
    private View view;

    public AdEntity() {

    }
    protected AdEntity(Parcel in) {
        Id = in.readLong();
        AimsId = in.readLong();
        AdType = in.readInt();
        JumpType = in.readInt();
        ImageName = in.readString();
        ImageAddress = in.readString();
        WebsiteAddress = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(Id);
        dest.writeLong(AimsId);
        dest.writeInt(AdType);
        dest.writeInt(JumpType);
        dest.writeString(ImageName);
        dest.writeString(ImageAddress);
        dest.writeString(WebsiteAddress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdEntity> CREATOR = new Creator<AdEntity>() {
        @Override
        public AdEntity createFromParcel(Parcel in) {
            return new AdEntity(in);
        }

        @Override
        public AdEntity[] newArray(int size) {
            return new AdEntity[size];
        }
    };

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getAimsId() {
        return AimsId;
    }

    public void setAimsId(long aimsId) {
        AimsId = aimsId;
    }

    public int getAdType() {
        return AdType;
    }

    public void setAdType(int adType) {
        AdType = adType;
    }

    public int getJumpType() {
        return JumpType;
    }

    public void setJumpType(int jumpType) {
        JumpType = jumpType;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageAddress() {
        return ImageAddress;
    }

    public void setImageAddress(String imageAddress) {
        ImageAddress = imageAddress;
    }

    public String getWebsiteAddress() {
        return WebsiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        WebsiteAddress = websiteAddress;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
