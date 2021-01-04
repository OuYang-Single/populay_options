package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.view.MenuItem;

import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void setBottomNavigationItem(BottomNavigationView mNavView, int i) {
      //  mModel.setBottomNavigationItem(mNavView,i);
    }

    public void RestoreSelected(int id) {
        int image=  R.mipmap.ic_home_black_click;
        switch (id) {
            case R.id.navigation_home:
                image=    R.mipmap.ic_home_black_click;
                break;
            case R.id.navigation_periphery:
                image=    R.mipmap.ic_quotes_black_click;
                break;
            case R.id.navigation_mine:
                image=    R.mipmap.ic_mine_black_click;
                break;
        }
        mRootView.RestoreSelected(image);
    }

    public void RestoreIcon() {

    }


}
