package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

public class DetailsPresenter extends BasePresenter<DetailsContract.Model, DetailsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    DetailsAdapter mDetailsAdapter;
    @Inject
    List<CommentsEntity> commentsEntities;
    @Inject
    public DetailsPresenter(DetailsContract.Model model, DetailsContract.View rootView) {
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
        commentsEntities.add(new CommentsEntity());
        commentsEntities.add(new CommentsEntity());
        commentsEntities.add(new CommentsEntity());
        mDetailsAdapter.notifyDataSetChanged();
    }
}