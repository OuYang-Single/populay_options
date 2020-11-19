package com.pine.populay_options.mvp.model.entity;

import java.util.Date;

public class ExchangEreal {
   private String  FS;//唯一完整编码
   private String  S;//品种代码
   private double  P;//最新价
   private String  NV;//最新成交量
   private Date Time;//时间
   private Long Tick;//时间戳
   private double H;//当天最高价
   private double L;//当天最低价
   private double YC;//昨收价
   private double B1;//--买
   private double S1;// --卖
   private double V;//当天交易量
   private double SY;//市盈率
   private double HS;//换手率（可能有）
   private double ZS;//总市值（可能有）= Z*P
   private double LS;//流通市值（可能有）= Z2*P
   private double Z;//发行量（可能有）
   private double Z2;//流通量（可能有）
   private double VF;//振幅（可能有）
   private double ZF;//-涨跌幅 = (P-YC)/YC
   private double ZD;//  --涨跌 = (P-YC)
   private double JS;//  结算价（可能有）
   private double YJS;// -昨结算价（可能有）
   private String A2;// --附加,状态0正常3停牌-3退市(CNST),行权日(CNETF). (如果有)
   private String M1;// -当前1分周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String M5;// -当前5分周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String M10;// -当前10分周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String M15;// -当前15分周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String M30;// -当前30分周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String H1;// -当前1小时周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String H2;// -当前2小时周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String H4;// -当前4小时周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)
   private String D;// -当前1天周期k线（可能有）,字段间逗号分隔（字段意义为：秒时间戳,开盘价,最高价,最低价,量。其中收盘价统一为当前价P)

    public String getFS() {
        return FS;
    }

    public void setFS(String FS) {
        this.FS = FS;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public String getNV() {
        return NV;
    }

    public void setNV(String NV) {
        this.NV = NV;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public Long getTick() {
        return Tick;
    }

    public void setTick(Long tick) {
        Tick = tick;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getYC() {
        return YC;
    }

    public void setYC(double YC) {
        this.YC = YC;
    }

    public double getV() {
        return V;
    }

    public void setV(double v) {
        V = v;
    }

    public double getSY() {
        return SY;
    }

    public void setSY(double SY) {
        this.SY = SY;
    }

    public double getHS() {
        return HS;
    }

    public void setHS(double HS) {
        this.HS = HS;
    }

    public double getZS() {
        return ZS;
    }

    public void setZS(double ZS) {
        this.ZS = ZS;
    }

    public double getLS() {
        return LS;
    }

    public void setLS(double LS) {
        this.LS = LS;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        Z = z;
    }

    public double getZ2() {
        return Z2;
    }

    public void setZ2(double z2) {
        Z2 = z2;
    }

    public double getVF() {
        return VF;
    }

    public void setVF(double VF) {
        this.VF = VF;
    }

    public double getZF() {
        return ZF;
    }

    public void setZF(double ZF) {
        this.ZF = ZF;
    }

    public double getZD() {
        return ZD;
    }

    public void setZD(double ZD) {
        this.ZD = ZD;
    }

    public double getJS() {
        return JS;
    }

    public void setJS(double JS) {
        this.JS = JS;
    }

    public double getYJS() {
        return YJS;
    }

    public void setYJS(double YJS) {
        this.YJS = YJS;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public double getB1() {
        return B1;
    }

    public void setB1(double b1) {
        B1 = b1;
    }

    public double getS1() {
        return S1;
    }

    public void setS1(double s1) {
        S1 = s1;
    }

    public String getM1() {
        return M1;
    }

    public void setM1(String m1) {
        M1 = m1;
    }

    public String getM5() {
        return M5;
    }

    public void setM5(String m5) {
        M5 = m5;
    }

    public String getM10() {
        return M10;
    }

    public void setM10(String m10) {
        M10 = m10;
    }

    public String getM15() {
        return M15;
    }

    public void setM15(String m15) {
        M15 = m15;
    }

    public String getM30() {
        return M30;
    }

    public void setM30(String m30) {
        M30 = m30;
    }

    public String getH1() {
        return H1;
    }

    public void setH1(String h1) {
        H1 = h1;
    }

    public String getH2() {
        return H2;
    }

    public void setH2(String h2) {
        H2 = h2;
    }

    public String getH4() {
        return H4;
    }

    public void setH4(String h4) {
        H4 = h4;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }
}
