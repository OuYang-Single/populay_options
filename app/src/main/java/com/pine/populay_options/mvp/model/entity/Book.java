package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int BookImg;
    private String BookImgString;
    private String BookName;
    private String Author;
    private String Introduction;
    public Book(int BookImg,String BookImgString,String BookName,String Author,String Introduction){
        this.BookImg=BookImg;
        this.BookImgString=BookImgString;
        this.BookName=BookName;
        this.Author=Author;
        this.Introduction=Introduction;
    }
    public Book(){}
    protected Book(Parcel in) {
        BookImg = in.readInt();
        BookImgString = in.readString();
        BookName = in.readString();
        Author = in.readString();
        Introduction = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getBookImg() {
        return BookImg;
    }

    public void setBookImg(int bookImg) {
        BookImg = bookImg;
    }

    public String getBookImgString() {
        return BookImgString;
    }

    public void setBookImgString(String bookImgString) {
        BookImgString = bookImgString;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(BookImg);
        dest.writeString(BookImgString);
        dest.writeString(BookName);
        dest.writeString(Author);
        dest.writeString(Introduction);
    }
}
