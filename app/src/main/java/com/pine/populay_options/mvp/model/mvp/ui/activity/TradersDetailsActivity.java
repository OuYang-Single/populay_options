package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersDetailsComponent;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersDetailsContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersDetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersPresenter;

import butterknife.BindView;

public class TradersDetailsActivity extends BaseAseActivitys<TradersDetailsPresenter> implements TradersDetailsContract.View{
  @BindView(R.id.toolbar_back)
  RelativeLayout mToolbarBack;
  @BindView(R.id.value)
  TextView mTextView;
  @BindView(R.id.img_traders_details)
  ImageView img_traders_details;
  TradersEntity mTradersEntity;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTradersDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_traders_details;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTradersEntity=   getIntent().getParcelableExtra("TradersEntity");
        setTitle(mTradersEntity.getTradersName());
        mToolbarBack.setVisibility(View.VISIBLE);
        mTextView.setText(mTradersEntity.getArticle());
        img_traders_details.setImageResource(mTradersEntity.getImage());
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
