package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.PositionOrder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class PaperAdapter extends DefaultAdapter<PositionOrder> {
    @Inject
    public PaperAdapter(List<PositionOrder> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<PositionOrder> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.paper_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<PositionOrder> {

        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull PositionOrder data, int position) {


        }
    }
}
