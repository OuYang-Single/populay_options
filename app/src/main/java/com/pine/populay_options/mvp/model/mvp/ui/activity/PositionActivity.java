package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPositionComponent;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PositionPresenter;
@Route(path = "/analogDisk/position")
public class PositionActivity  extends BaseAseActivitys<PositionPresenter> implements PositionContract.View{
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPositionComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_position;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
