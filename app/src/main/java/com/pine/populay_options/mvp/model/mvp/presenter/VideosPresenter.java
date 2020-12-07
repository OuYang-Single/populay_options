package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Brokers;
import com.pine.populay_options.mvp.model.entity.Videos;
import com.pine.populay_options.mvp.model.mvp.contract.BrokersContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
@ActivityScope
public class VideosPresenter extends BasePresenter<VideosContract.Model, VideosContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<Videos> videosList;
    @Inject
    public VideosPresenter(VideosContract.Model model, VideosContract.View rootView) {
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
        videosList.add(new Videos());
        videosList.add(new Videos());
        videosList.add(new Videos());
    }
}