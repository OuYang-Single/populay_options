package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.mvp.model.entity.PositionOrder;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PaperAdapter;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
@ActivityScope
public class PaperFragmentPresenter extends BasePresenter<PaperFragmentContract.Model, PaperFragmentContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    PaperAdapter mPaperAdapter;
    @Inject
    List<PositionOrder> positionOrderList;
    @Inject
    public PaperFragmentPresenter(PaperFragmentContract.Model model, PaperFragmentContract.View rootView) {
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
        positionOrderList.add(new PositionOrder());
        positionOrderList.add(new PositionOrder());
        positionOrderList.add(new PositionOrder());
        positionOrderList.add(new PositionOrder());
        positionOrderList.add(new PositionOrder());
        mPaperAdapter.notifyDataSetChanged();
    }
}
