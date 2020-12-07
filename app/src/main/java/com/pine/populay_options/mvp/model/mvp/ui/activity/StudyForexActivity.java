package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerStudyForexComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideoComponent;
import com.pine.populay_options.mvp.model.mvp.contract.StudyForexContract;
import com.pine.populay_options.mvp.model.mvp.presenter.StudyForexPresenter;

import butterknife.BindView;

public class StudyForexActivity extends BaseActivity<StudyForexPresenter> implements StudyForexContract.View {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerStudyForexComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.study_forex_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.study_forex);
        mToolbarBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
