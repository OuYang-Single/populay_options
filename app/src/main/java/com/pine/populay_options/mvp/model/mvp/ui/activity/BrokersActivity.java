package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerAddDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerBrokersComponent;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.BrokersContract;
import com.pine.populay_options.mvp.model.mvp.presenter.AddDetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.BrokersPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.BrokersAdapter;

import javax.inject.Inject;

import butterknife.BindView;
@Route(path = "/analogDisk/brokers")
public class BrokersActivity extends BaseActivity<BrokersPresenter> implements BrokersContract.View{
    @BindView(R.id.recycler_brokers)
    RecyclerView mRecyclerBrokers;
    @Inject
    BrokersAdapter mBrokersAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBrokersComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_brokers;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.top_brokers);
        mRecyclerBrokers.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerBrokers.setAdapter(mBrokersAdapter);
        mPresenter.initData();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
