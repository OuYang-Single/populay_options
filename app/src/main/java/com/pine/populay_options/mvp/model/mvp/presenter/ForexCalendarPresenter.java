package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.entity.ForexCalendar;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class ForexCalendarPresenter extends BasePresenter<ForexCalendarContract.Model, ForexCalendarContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<ForexCalendar> forexCalendars;
    @Inject
    public ForexCalendarPresenter(ForexCalendarContract.Model model, ForexCalendarContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void initData() {
        forexCalendars.clear();
        forexCalendars.add(new ForexCalendar("07:50","JPY","Private Non-Residential Investment q/q"));
        forexCalendars.add(new ForexCalendar("07:50","JPY","Economy Watchers Index for Current Conditions"));
        mRootView.setTime(mModel.getTime());
    }

    public void Future(String text) {
        mRootView.setTime(mModel.Future(text));
    }

    public void Previous(String text) {
        mRootView.setTime(mModel.Previous(text));
    }
}