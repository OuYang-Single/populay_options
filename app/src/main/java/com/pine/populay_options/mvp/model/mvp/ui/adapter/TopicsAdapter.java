package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Billboard;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.yds.library.MultiImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TopicsAdapter  extends DefaultAdapter<Topics> {
    @Inject
    public TopicsAdapter(List<Topics> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Topics> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.topics_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<Topics> {
        @BindView(R.id.image_multi)
        MultiImageView mMultiImageView;

        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Topics data, int position) {
            if (data.getSrc()!=null){
                mMultiImageView.setImagesData(data.getSrc());//设置数据
            }


        }
    }
}