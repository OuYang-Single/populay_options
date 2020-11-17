package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
@ActivityScope
public class DemoTradingPresenter extends BasePresenter<DemoTradingContract.Model, DemoTradingContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public DemoTradingPresenter(DemoTradingContract.Model model, DemoTradingContract.View rootView) {
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

}
