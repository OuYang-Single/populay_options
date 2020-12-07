package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;
import android.text.format.DateFormat;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;

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
        String dateFormat = "d MMMM";
        String dateFormats=   DateFormat.format(dateFormat, new Date().getTime()).toString();
        String weekFormat ="EEEE";
        String weekFormats= DateFormat.format(weekFormat, new Date().getTime()).toString();
        return dateFormats+","+weekFormats;
    }
}