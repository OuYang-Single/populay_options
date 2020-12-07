package com.pine.populay_options.mvp.model.entity;

public class Brokers {
          private String Name;
          private int  img;
          private String Max;
          private String Min;
          private String Pairs;
    public Brokers(){

    }
    public Brokers(int img,String Max,String min ,String pairs){
        this.img=img;
        this.Min=min;
        this.Max=Max;
        this.Pairs=pairs;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMax() {
        return Max;
    }

    public void setMax(String max) {
        Max = max;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String min) {
        Min = min;
    }

    public String getPairs() {
        return Pairs;
    }

    public void setPairs(String pairs) {
        Pairs = pairs;
    }
}
