package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPlayersFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PlayersFragmentPresenter;

public class PlayersFragment  extends BaseFragment<PlayersFragmentPresenter> implements PlayersFragmentContract.View{
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerPlayersFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_players, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
