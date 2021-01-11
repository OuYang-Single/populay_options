package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.AdEntity;


import java.util.List;

/*主页广告 ViewPager的适配器*/
public class HomeItemAdPagerAdapter extends PagerAdapter {
    private List<AdEntity> mAdEntities;
    private Context mContext;
    private onClickHomeItmeAdListener OnClickHomeItmeAdListener;

    public void setOnClickHomeItmeAdListener(onClickHomeItmeAdListener onClickHomeItmeAdListener) {
        OnClickHomeItmeAdListener = onClickHomeItmeAdListener;
    }

    public   interface onClickHomeItmeAdListener{
        void onClick(View view,AdEntity adEntity,int position);
    }


    public HomeItemAdPagerAdapter(List<AdEntity> mAdEntities, Context mContext, onClickHomeItmeAdListener OnClickHomeItmeAdListener) {
        this.mAdEntities = mAdEntities;
        this.mContext = mContext;
        this.OnClickHomeItmeAdListener=OnClickHomeItmeAdListener;
    }

    @Override
    public int getCount() {
        return mAdEntities.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_item_ad_layout_img, container, false);
        ImageView imageView = view.findViewById(R.id.home_item_vp_ad_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OnClickHomeItmeAdListener!=null){
                    OnClickHomeItmeAdListener.onClick(view,mAdEntities.get(position),position);
                }
            }
        });
        Glide.with(mContext).load(mAdEntities.get(position).getImageAddress()).into(imageView);
        (container).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
