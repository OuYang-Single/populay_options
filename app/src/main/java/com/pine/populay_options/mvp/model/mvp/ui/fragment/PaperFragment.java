package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PaperAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class PaperFragment extends BaseFragment<PaperFragmentPresenter> implements PaperFragmentContract.View, TabLayout.BaseOnTabSelectedListener {
    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.paper)
    RecyclerView mRecyclerView;
    @Inject
    PaperAdapter mPaperAdapter;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerPaperFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_paper, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTabLayout.addTab(mTabLayout.newTab().setText("Position"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Ordering"));
        mTabLayout.addTab(mTabLayout.newTab().setText("History"));
        mTabLayout.setOnTabSelectedListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPaperAdapter);
        mPresenter.initData();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
