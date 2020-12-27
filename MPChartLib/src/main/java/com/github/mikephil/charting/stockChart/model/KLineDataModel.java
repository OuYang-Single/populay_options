package com.github.mikephil.charting.stockChart.model;





import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/8.
 */
public class KLineDataModel implements Serializable {
    public String m_szInstrumentID;// 合约ID

    //时间戳 "C": 16.47, 		--收盘价
    //      "Tick": 1529856000,	--标准时间戳
    //      "O": 16.59,		--开盘价
    //      "H": 16.62,		--最高
    //      "L": 16.4,		--最低
    //      "A": 352461552,		--交易额
    //      "V": 21345191,		--交易量
    //      "D":"2018-01-01"          --日期


    private String D ;
    private Long Tick ;
   
    private double H;// 最高价
  
    private double L;// 最低价
   
    private double O;// 开盘价
  
    private double C;// 收盘价

    private double V;// 成交量

 
    private double A;// 成交额


    private double preClose;// 昨收价
    private double ma5;
    private double ma10;
    private double ma20;
    private double ma30;
    private double ma60;

    public String getDateMills() {
        return D;
    }

    public void setDateMills(String D) {
        this.D = D;
    }

    public double getHigh() {
        return H;
    }

    public void setHigh(double high) {
        this.H = high;
    }

    public double getLow() {
        return L;
    }

    public void setLow(double L) {
        this.L = L;
    }

    public double getOpen() {
        return O;
    }

    public void setOpen(double O) {
        this.O = O;
    }

    public double getClose() {
        return C;
    }

    public void setClose(double close) {
        this.C = close;
    }

    public double getVolume() {
        return V;
    }

    public void setVolume(double volume) {
        this.V = volume;
    }

    public double getTotal() {
        return A ;}

    public void setTotal(double A) {
        this.A = A;
    }

    public double getPreClose() {
        return preClose;
    }

    public void setPreClose(double preClose) {
        this.preClose = preClose;
    }

    public double getMa5() {
        return ma5;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public double getMa20() {
        return ma20;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }

    public double getMa60() {
        return ma60;
    }

    public void setMa60(double ma60) {
        this.ma60 = ma60;
    }

    public Long getTick() {
        return Tick;
    }

    public void setTick(Long tick) {
        Tick = tick;
    }
}
