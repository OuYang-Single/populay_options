package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dld.view.SegmentedControlItem;
import com.dld.view.SegmentedControlView;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPositionComponent;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PositionPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.DemoTradingFragment;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PaperFragment;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PlayersFragment;
import com.pine.populay_options.mvp.model.wigth.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = "/analogDisk/position")
public class PositionActivity  extends BaseAseActivitys<PositionPresenter> implements PositionContract.View, SegmentedControlView.OnSegItemClickListener {
    @BindView(R.id.scv)
    SegmentedControlView mScv;
    @BindView(R.id.vp_content)
    NoScrollViewPager mNoScrollViewPager;
    @Inject
    List<Fragment>  fragments ;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPositionComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_position;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.addItems();
        mPresenter.initViewPager();


    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void addItems(List<SegmentedControlItem> items) {
        mScv.addItems(items);
        mScv.setOnSegItemClickListener(this);
    }

    @Override
    public void initViewPager() {
        mNoScrollViewPager.setOffscreenPageLimit(fragments.size());
        mNoScrollViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "";
            }
        });

    }

    @Override
    public void onItemClick(SegmentedControlItem item, int position) {
               mNoScrollViewPager.setCurrentItem(position);
    }
}
