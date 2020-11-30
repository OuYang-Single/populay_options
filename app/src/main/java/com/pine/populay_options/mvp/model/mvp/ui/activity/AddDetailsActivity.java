package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerAddDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.presenter.AddDetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.DetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class AddDetailsActivity extends BaseActivity<AddDetailsPresenter> implements AddDetailsContract.View{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.new_topic);
        mTvRightToolbar.setText(R.string.publish);
        mTvRightToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
