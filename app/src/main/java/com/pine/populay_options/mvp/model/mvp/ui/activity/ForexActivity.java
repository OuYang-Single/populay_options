package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerForexComponent;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.presenter.CustomerPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.ForexPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ForexAdapter;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = "/analogDisk/Forex")
public class ForexActivity extends BaseActivity<ForexPresenter> implements ForexContract.View, DefaultAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.recycler_forex)
    RecyclerView mRecyclerForex;
    @Inject
    ForexAdapter mForexAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerForexComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.forex_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.forex);
        mRecyclerForex.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerForex.setAdapter(mForexAdapter);
        mForexAdapter.setOnItemClickListener(this);
        mToolbarBack.setVisibility(View.VISIBLE);
        mPresenter.initData();

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {

        Intent   intent=new Intent(this, BookDetalisActivity.class);
        intent.putExtra("Book", mForexAdapter.getInfos().get(position));
        startActivity(intent);
    }
}
