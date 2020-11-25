package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPlayersFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PlayersFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PaperAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PlayersAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class PlayersFragment  extends BaseFragment<PlayersFragmentPresenter> implements PlayersFragmentContract.View{
    @BindView(R.id.img_crown_1)
    ImageView mImgCrown1;
    @BindView(R.id.img_crown_2)
    ImageView mImgCrown2;
    @BindView(R.id.img_crown_3)
    ImageView mImgCrown3;
    /* 角度变化的矩阵 */
    private Matrix matrix = new Matrix ( );
    @BindView(R.id.paper)
    RecyclerView mRecyclerView;
    @Inject
    PlayersAdapter mPlayersAdapter;
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
        Bitmap bitmap1 = ( (BitmapDrawable) ( getResources ( )
                .getDrawable ( R.mipmap.ic_crown_1 ) ) )
                .getBitmap ( );
        Bitmap bitmap2 = ( (BitmapDrawable) ( getResources ( )
                .getDrawable ( R.mipmap.ic_crown_3 ) ) )
                .getBitmap ( );
        Bitmap bitmap3 = ( (BitmapDrawable) ( getResources ( )
                .getDrawable ( R.mipmap.ic_crown_2 ) ) )
                .getBitmap ( );
        // 设置图片旋转的角度
        matrix.setRotate ( 45 );
        bitmap1 = Bitmap.createBitmap (
                bitmap1 ,
                0 ,
                0 ,
                bitmap1.getWidth ( ) ,
                bitmap1.getHeight ( ) ,
                matrix ,
                true );
        bitmap2 = Bitmap.createBitmap (
                bitmap2 ,
                0 ,
                0 ,
                bitmap2.getWidth ( ) ,
                bitmap2.getHeight ( ) ,
                matrix ,
                true );
        bitmap3 = Bitmap.createBitmap (
                bitmap3 ,
                0 ,
                0 ,
                bitmap3.getWidth ( ) ,
                bitmap3.getHeight ( ) ,
                matrix ,
                true );
        mImgCrown1.setImageBitmap ( bitmap1 );
        mImgCrown2.setImageBitmap ( bitmap2 );
        mImgCrown3.setImageBitmap ( bitmap3 );
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPlayersAdapter);
        mPresenter.initData();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
