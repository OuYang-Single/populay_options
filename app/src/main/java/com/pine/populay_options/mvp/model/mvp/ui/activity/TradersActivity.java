package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerMainComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersComponent;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.presenter.PositionPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TradersAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class TradersActivity extends BaseAseActivitys<TradersPresenter> implements TradersContract.View, DefaultAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Inject
    TradersAdapter mTradersAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTradersComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_traders;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.traders_title);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mTradersAdapter);
        mPresenter.initData();
        mTradersAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
        Intent mIntent=   new Intent(this, TradersDetailsActivity.class);
        startActivity(mIntent);
    }
}
