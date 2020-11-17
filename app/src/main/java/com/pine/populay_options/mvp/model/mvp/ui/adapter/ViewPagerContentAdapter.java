package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerContentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public ViewPagerContentAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList=mFragmentList;
    }

    ;

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    // 初始化每个页面选项
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        return super.instantiateItem(container, position);
    }
    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }
}
