package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerCustomerComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideoComponent;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.presenter.CustomerPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.VideoPresenter;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

public class CustomerActivity extends BaseActivity<CustomerPresenter> implements CustomerContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCustomerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.customer_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mPresenter.initData();

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
