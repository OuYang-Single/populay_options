package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Billboard;
import com.pine.populay_options.mvp.model.entity.PositionOrder;

import java.util.List;

import javax.inject.Inject;

public class PlayersAdapter  extends DefaultAdapter<Billboard> {
    @Inject
    public PlayersAdapter(List<Billboard> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Billboard> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.player_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<Billboard> {

        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Billboard data, int position) {


        }
    }
}