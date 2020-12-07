package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Brokers;
import com.pine.populay_options.mvp.model.entity.Videos;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class VideosAdapter extends DefaultAdapter<Videos> {
    @Inject
    public VideosAdapter(List<Videos> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Videos> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.videos_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<Videos> {


        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Videos data, int position) {

        }
    }
}