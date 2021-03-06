package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerMainComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.ImageInfo;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.presenter.MainPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;
import com.pine.populay_options.mvp.model.wigth.NoScrollViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;

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
        mVpContent.setOffscreenPageLimit(3);
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
            case R.id.navigation_message:
                selectedItemId = item.getItemId();
                item.setIcon(R.mipmap.dynamic_c);
                mVpContent.setCurrentItem(1);
                setStatusBarMode(this, true , Color.parseColor("#ffffff"));
                return true;
            case R.id.navigation_periphery:
                selectedItemId = item.getItemId();
                item.setIcon(R.mipmap.ic_quotes_black_click);
                mVpContent.setCurrentItem(2);
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
    public Drawable zoomImage(int resId, int w, int h,int colors){
        Resources res = getResources();
        Bitmap oldBmp = BitmapFactory.decodeResource(res, resId);
        Bitmap newBmp = Bitmap.createScaledBitmap(oldBmp,w, h, true);
        Drawable drawable = new BitmapDrawable(res, newBmp);

        return setDrawable(drawable,colors);
    }
    public Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
    private Drawable setDrawable( Drawable drawable, int color) {
        drawable = tintDrawable(drawable, ColorStateList.valueOf(color));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int size;
        size = 90 * dm.heightPixels / 2560;
        drawable.setBounds(0, 0, size, size);
       return drawable;
    }
    private void RestoreIcon() {
        mNavView.getMenu().findItem(R.id.navigation_home).setIcon(R.mipmap.ic_home_black);
        mNavView.getMenu().findItem(R.id.navigation_periphery).setIcon(R.mipmap.ic_quotes_black);
        mNavView.getMenu().findItem(R.id.navigation_message).setIcon(R.mipmap.dynamic);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        }else if (requestCode == PICK_REQUEST_CODES && resultCode == Activity.RESULT_OK){
            ArrayList<String> img = data.getStringArrayListExtra("data");
            if (!img.isEmpty()){
                ImageInfo imageInfo=new ImageInfo();
                imageInfo.setImg(img.get(0));
                EventBus.getDefault().post(imageInfo);
              /*  mPresenter.modifyAvatar( img.get(0),UserAll.get(0).getId());*/
            }
        }
    }
}
