package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Brokers;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.mvp.contract.BrokersContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

public class BrokersPresenter  extends BasePresenter<BrokersContract.Model, BrokersContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<Brokers> brokersList;
    @Inject
    public BrokersPresenter(BrokersContract.Model model, BrokersContract.View rootView) {
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

    public void initData() {//int img,String Max,String min ,String pairs
        brokersList.add(new Brokers(R.mipmap.u346,"1:888","0.6 pips","55+"));
        brokersList.add(new Brokers(R.mipmap.u355,"1:1000","0 pips","57"));
        brokersList.add(new Brokers(R.mipmap.u361,"1:1000","0 pips","55+"));
        brokersList.add(new Brokers(R.mipmap.u367,"1:1000","0 pips","49"));
        brokersList.add(new Brokers(R.mipmap.u367,"1:400","0.9 pips","64"));
        brokersList.add(new Brokers(R.mipmap.u373,"1:3000","0 pips","35+"));
        brokersList.add(new Brokers(R.mipmap.u379,"1:400","0.6 pips","60"));
        brokersList.add(new Brokers(R.mipmap.u385,"1:500","0 pips","53"));
        brokersList.add(new Brokers(R.mipmap.u391,"1:500","0.4 pips","60+"));
        brokersList.add(new Brokers(R.mipmap.u403,"1:500","0 pips","40+"));
        brokersList.add(new Brokers(R.mipmap.u410,"1:200","0.1 pips","38"));
        brokersList.add(new Brokers(R.mipmap.u416,"--","0.25 pips","10"));
        brokersList.add(new Brokers(R.mipmap.u422,"1:400","0 pips","40+"));
        brokersList.add(new Brokers(R.mipmap.u428,"--","1.4 pips","80+"));
        brokersList.add(new Brokers(R.mipmap.u434,"1:500","0 pips","61+"));
        brokersList.add(new Brokers(R.mipmap.u440,"1:1000","0 pips","188"));
        brokersList.add(new Brokers(R.mipmap.u446,"1:2000","0 pips","40+"));
        brokersList.add(new Brokers(R.mipmap.u452,"1:500","0.2 pips","76"));
        brokersList.add(new Brokers(R.mipmap.u458,"1:500","0.1 pips","49"));
        brokersList.add(new Brokers(R.mipmap.u464,"1:500","0 pips","28"));
    }
}