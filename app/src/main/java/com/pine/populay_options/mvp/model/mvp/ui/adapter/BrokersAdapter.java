package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Brokers;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class BrokersAdapter extends DefaultAdapter<Brokers> {
    @Inject
    public BrokersAdapter(List<Brokers> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Brokers> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.brokers_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<Brokers> {
        @BindView(R.id.brokers_rank)
        TextView brokers_rank;
        @BindView(R.id.tv_brokers_item_max)
        TextView tv_brokers_item_max;
        @BindView(R.id.tv_brokers_item_min)
        TextView tv_brokers_item_min;
        @BindView(R.id.tv_brokers_item_pairs)
        TextView tv_brokers_item_pairs;
        @BindView(R.id.brokers_img)
        ImageView brokers_img;

        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Brokers data, int position) {
            int i=position+1;
            brokers_rank.setText(i+"");
            brokers_img.setImageResource(data.getImg());
            tv_brokers_item_max.setText(data.getMax());
            tv_brokers_item_min.setText(data.getMin());
            tv_brokers_item_pairs.setText(data.getPairs());
        }
    }
}