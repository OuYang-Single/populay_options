package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.yds.library.MultiImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsAdapter extends DefaultAdapter<CommentsEntity> {
    @Inject
    public DetailsAdapter(List<CommentsEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<CommentsEntity> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.details_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<CommentsEntity> {


        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull CommentsEntity data, int position) {

        }
    }
}