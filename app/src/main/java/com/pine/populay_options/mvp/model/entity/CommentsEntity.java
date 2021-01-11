package com.pine.populay_options.mvp.model.entity;

import java.util.Date;
import java.util.List;

public class CommentsEntity {
    /*    private int Image;
        private String Time;
        private String Title;
        private String Content;
        private int Like;
        private int Comment;
        private boolean Report;*/
    private Integer id;
    private Integer topicsId;
    private long createTime;
    private long updateTime;
    private String comment;
    private String image;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(Integer topicsId) {
        this.topicsId = topicsId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
