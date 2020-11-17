package com.pine.populay_options.mvp.model.wigth.chart.formatter;


import com.pine.populay_options.app.utils.DateUtil;
import com.pine.populay_options.mvp.model.wigth.chart.base.IDateTimeFormatter;

import java.util.Date;

/**
 * 时间格式化器
 * Created by tifezh on 2016/6/21.
 */

public class DateFormatter implements IDateTimeFormatter {
    @Override
    public String format(Date date) {
        if (date != null) {
            return DateUtil.DateFormat.format(date);
        } else {
            return "";
        }
    }
}
