package com.pine.populay_options.mvp.model.mvp.contract;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentManager;

import com.dld.view.SegmentedControlItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

public interface PositionContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void addItems(List<SegmentedControlItem> items);

        void initViewPager();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}