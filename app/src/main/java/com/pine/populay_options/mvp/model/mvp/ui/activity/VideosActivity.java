package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideosComponent;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.VideosPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TradersAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.VideosAdapter;

import javax.inject.Inject;

import butterknife.BindView;
@Route(path = "/analogDisk/videos")
public class VideosActivity extends BaseActivity<VideosPresenter> implements VideosContract.View, DefaultAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.recycler_videos)
    RecyclerView mRecyclerVideos;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @Inject
    VideosAdapter videosAdapter;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerVideosComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.videos_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.videos);
        mToolbarBack.setVisibility(View.VISIBLE);
        mRecyclerVideos.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerVideos.setAdapter(videosAdapter);
        videosAdapter.setOnItemClickListener(this);
        mPresenter.initData();

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
        Intent intent=new Intent(this,VideoActivity.class);
        intent.putExtra("video",videosAdapter.getInfos().get(position));
        startActivity(intent);
    }
}
