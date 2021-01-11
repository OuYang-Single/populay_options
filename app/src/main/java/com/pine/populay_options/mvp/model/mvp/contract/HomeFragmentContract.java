package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.AdEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;

import io.reactivex.Observable;

public interface HomeFragmentContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView, HomeItemAdPagerAdapter.onClickHomeItmeAdListener {

        Activity getActivity();

        @Override
        void showMessage(@NonNull String message);

        @Override
        void onClick(android.view.View view, AdEntity adEntity, int position);
    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        void initData();
    }
}