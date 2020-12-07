package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerVideoComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideosComponent;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.presenter.VideoPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.VideosPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.VideosAdapter;

import javax.inject.Inject;

import butterknife.BindView;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

@Route(path = "/analogDisk/video")
public class VideoActivity extends BaseActivity<VideoPresenter> implements VideoContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerVideoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setFullscreen(this);
        return R.layout.video_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mPresenter.initData();

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
