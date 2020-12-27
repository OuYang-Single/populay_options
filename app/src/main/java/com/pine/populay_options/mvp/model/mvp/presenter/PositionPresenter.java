package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PaperFragment;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PlayersFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@ActivityScope
public class PositionPresenter extends BasePresenter<PositionContract.Model, PositionContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<Fragment>  fragments ;

    @Inject
    public PositionPresenter(PositionContract.Model model, PositionContract.View rootView) {
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

    public void addItems() {
     /*   List<SegmentedControlItem> items = new ArrayList<>();
        items.add(new SegmentedControlItem( "My paper"));
        items.add(new SegmentedControlItem("Top players"));
        mRootView.addItems(items);*/
    }

    public void initViewPager() {
        fragments.add(new PaperFragment());
        fragments.add(new PlayersFragment());
        mRootView.initViewPager();
    }
}
