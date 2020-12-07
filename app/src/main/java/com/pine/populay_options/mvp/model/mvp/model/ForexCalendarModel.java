package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;
import android.text.format.DateFormat;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

public class ForexCalendarModel extends BaseModel implements ForexCalendarContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ForexCalendarModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public String getTime() {
        String dateFormat = "d MMMM,EEEE";
        String dateFormats=   DateFormat.format(dateFormat, new Date().getTime()).toString();

        return dateFormats;
    }

    @Override
    public String Future(String string) {
        String dateFormat = "d MMMM,EEEE";
        DateFormat.format(dateFormat, new Date().getTime()).toString();
        Long timestamp = null;
        String dateFormats=string;
        try {
            timestamp=   new SimpleDateFormat(dateFormat).parse(string).getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(timestamp));
            c.add(Calendar.DAY_OF_MONTH, 1);
             dateFormats=   DateFormat.format(dateFormat, c.getTime()).toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormats;
    }

    @Override
    public String Previous(String text) {
        String dateFormat = "d MMMM,EEEE";
        DateFormat.format(dateFormat, new Date().getTime()).toString();
        Long timestamp = null;
        String dateFormats=text;
        try {
            timestamp=   new SimpleDateFormat(dateFormat).parse(text).getTime();
            timestamp=timestamp-(long)1 * 24 * 60 * 60 * 1000;
            dateFormats=   DateFormat.format(dateFormat, timestamp).toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormats;
    }
}