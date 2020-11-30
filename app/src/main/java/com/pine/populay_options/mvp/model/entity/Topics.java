package com.pine.populay_options.mvp.model.entity;


import java.util.List;

public class Topics {
    private int Image;
    private String Time;
    private String Title;
    private String Content;
    private int Like;
    private int Comment;
    private List<String> ImageInfo;
    private List<Integer> src ;
    private int[] ints;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getLike() {
        return Like;
    }

    public void setLike(int like) {
        Like = like;
    }

    public int getComment() {
        return Comment;
    }

    public void setComment(int comment) {
        Comment = comment;
    }


    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    public List<String> getImageInfo() {
        return ImageInfo;
    }

    public void setImageInfo(List<String> imageInfo) {
        ImageInfo = imageInfo;
    }

    public List<Integer> getSrc() {
        return src;
    }

    public void setSrc(List<Integer> src) {
        this.src = src;
    }
}
