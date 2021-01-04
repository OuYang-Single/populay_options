package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerMainComponent;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.presenter.MainPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;
import com.pine.populay_options.mvp.model.wigth.NoScrollViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import javax.inject.Inject;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    BottomNavigationView mNavView;
    @BindView(R.id.vp_content)
    NoScrollViewPager mVpContent;
    int selectedItemId;
    @Inject
    ViewPagerContentAdapter mViewPagerContentAdapterl;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setFullscreen(this);
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        Resources resource=(Resources)getBaseContext().getResources();
        ColorStateList csl=(ColorStateList)resource.getColorStateList(R.color.navigation_menu_item_color);
        mNavView.setItemTextColor(csl);
        mNavView.setOnNavigationItemSelectedListener(this);
        mNavView.setItemIconTintList(null);    //删除默认的选中效果\
        mPresenter.setBottomNavigationItem(mNavView, 40);
        selectedItemId = mNavView.getSelectedItemId();
        mVpContent.setAdapter(mViewPagerContentAdapterl);
       // mVpContent.setCurrentItem(mNavView.getMaxItemCount());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        RestoreIcon();

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selectedItemId = item.getItemId();
                item.setIcon(R.mipmap.ic_home_black_click);
                mVpContent.setCurrentItem(0);
                setStatusBarMode(this, true , Color.parseColor("#ffffff"));
                return true;
            case R.id.navigation_periphery:
                selectedItemId = item.getItemId();
                item.setIcon(R.mipmap.ic_quotes_black_click);
                mVpContent.setCurrentItem(1);
                setStatusBarMode(this, true , Color.parseColor("#ffffff"));
                return true;

            case R.id.navigation_mine:
                selectedItemId = item.getItemId();
                item.setIcon(R.mipmap.ic_mine_black_click);
                mVpContent.setCurrentItem(3);
                setStatusBarMode(this, false , Color.parseColor("#2e343e"));
                return true;
        }
        return false;
    }

    private void RestoreIcon() {
        mNavView.getMenu().findItem(R.id.navigation_home).setIcon(R.mipmap.ic_home_black);
        mNavView.getMenu().findItem(R.id.navigation_periphery).setIcon(R.mipmap.ic_quotes_black);
        mNavView.getMenu().findItem(R.id.navigation_mine).setIcon(R.mipmap.ic_mine_black);
    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void statusService(Intent intent) {

    }

    @Override
    public void RestoreSelected(int image) {
        mNavView.getMenu().findItem(selectedItemId).setIcon(image);
    }

    @Override
    public FragmentManager getFragmentManagers() {
        return getSupportFragmentManager();
    }


}
