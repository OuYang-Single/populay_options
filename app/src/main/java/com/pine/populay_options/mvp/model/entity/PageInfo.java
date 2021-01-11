package com.pine.populay_options.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PageInfo <T> implements Parcelable {
    private int pageNum;
    private int pageSize;
    private int size;
    private String orderBy;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<T> list;
    private int firstPage;
    private int prePage;
    private int nextPage;
    private int lastPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int[] navigatepageNums;

    protected PageInfo(Parcel in) {
        pageNum = in.readInt();
        pageSize = in.readInt();
        size = in.readInt();
        orderBy = in.readString();
        startRow = in.readInt();
        endRow = in.readInt();
        total = in.readLong();
        pages = in.readInt();
        firstPage = in.readInt();
        prePage = in.readInt();
        nextPage = in.readInt();
        lastPage = in.readInt();
        isFirstPage = in.readByte() != 0;
        isLastPage = in.readByte() != 0;
        hasPreviousPage = in.readByte() != 0;
        hasNextPage = in.readByte() != 0;
        navigatePages = in.readInt();
        navigatepageNums = in.createIntArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pageNum);
        dest.writeInt(pageSize);
        dest.writeInt(size);
        dest.writeString(orderBy);
        dest.writeInt(startRow);
        dest.writeInt(endRow);
        dest.writeLong(total);
        dest.writeInt(pages);
        dest.writeInt(firstPage);
        dest.writeInt(prePage);
        dest.writeInt(nextPage);
        dest.writeInt(lastPage);
        dest.writeByte((byte) (isFirstPage ? 1 : 0));
        dest.writeByte((byte) (isLastPage ? 1 : 0));
        dest.writeByte((byte) (hasPreviousPage ? 1 : 0));
        dest.writeByte((byte) (hasNextPage ? 1 : 0));
        dest.writeInt(navigatePages);
        dest.writeIntArray(navigatepageNums);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PageInfo> CREATOR = new Creator<PageInfo>() {
        @Override
        public PageInfo createFromParcel(Parcel in) {
            return new PageInfo(in);
        }

        @Override
        public PageInfo[] newArray(int size) {
            return new PageInfo[size];
        }
    };

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
