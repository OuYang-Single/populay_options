package com.pine.populay_options.mvp.model.entity;

public class Billboard {
    private String BillboardName;
    private int Ranking;
    private int Portrait;
    private double Money;

    public String getBillboardName() {
        return BillboardName;
    }

    public void setBillboardName(String billboardName) {
        BillboardName = billboardName;
    }

    public int getRanking() {
        return Ranking;
    }

    public void setRanking(int ranking) {
        Ranking = ranking;
    }

    public int getPortrait() {
        return Portrait;
    }

    public void setPortrait(int portrait) {
        Portrait = portrait;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }
}
