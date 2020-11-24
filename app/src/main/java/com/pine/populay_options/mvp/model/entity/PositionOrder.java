package com.pine.populay_options.mvp.model.entity;

public class PositionOrder {
    private double Buying;//买入价
    private double Current;//当前价
    private int OrderType;//订单类型，0为持仓，1为挂单，2为历史
    private long ChangeTime;//订单类型，0为持仓，1为挂单，2为历史
    private long PositionType;//订单类型，0为买，1为买
    private double Position;//头寸
    private String Name;//

    public double getBuying() {
        return Buying;
    }

    public void setBuying(double buying) {
        Buying = buying;
    }

    public double getCurrent() {
        return Current;
    }

    public void setCurrent(double current) {
        Current = current;
    }

    public int getOrderType() {
        return OrderType;
    }

    public void setOrderType(int orderType) {
        OrderType = orderType;
    }

    public long getChangeTime() {
        return ChangeTime;
    }

    public void setChangeTime(long changeTime) {
        ChangeTime = changeTime;
    }

    public long getPositionType() {
        return PositionType;
    }

    public void setPositionType(long positionType) {
        PositionType = positionType;
    }

    public double getPosition() {
        return Position;
    }

    public void setPosition(double position) {
        Position = position;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
