package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.yds.library.MultiImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class TradersAdapter extends DefaultAdapter<TradersEntity> {
    @Inject
    ImageLoader mImageLoader;
    @Inject
    public TradersAdapter(List<TradersEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<TradersEntity> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.traders_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<TradersEntity> {
        @BindView(R.id.img_traders_item)
        ImageView img_traders_item;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull TradersEntity data, int position) {
            img_traders_item.setImageResource(data.getImage());
        }
    }
}