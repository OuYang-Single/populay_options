package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.MainPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsContract.View{
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Inject
    DetailsAdapter mDetailsAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
           return R.layout.activity_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDetailsAdapter);
        mPresenter.initData();
        mToolbarTitle.setText(R.string.details);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
