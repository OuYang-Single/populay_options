package com.pine.populay_options.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 * Created by tifezh on 2016/4/27.
 */
public class DateUtil {
    public static SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat shortTimeFormat = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public static String timeStamp2Date(Long auser) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        long signtime = ((long)(auser))*1000;
        String lastSignTime = dateFormat.format(new Date(signtime));


        return lastSignTime;
    }
}
