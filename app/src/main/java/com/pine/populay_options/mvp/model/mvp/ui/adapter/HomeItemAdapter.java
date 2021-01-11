package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeItemAdapter extends DefaultAdapter<ExchangEreal> {
    @Inject
    public HomeItemAdapter(List<ExchangEreal> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<ExchangEreal> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.quotes_items;
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {

        return new LinearLayoutManager(context);
    }

    public class ContextItemBaseHolder extends BaseHolder<ExchangEreal> {
        @BindView(R.id.tv_quotes_text)
        TextView tvQuotesText;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_percent)
        TextView tvPercent;


        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull ExchangEreal data, int position) {
            tvQuotesText.setText(data.getFS());
            tvPrice.setText(data.getP() + "");
            if (data.getZF() > 0) {
                tvPercent.setText("+"+ String.format("%.2f",data.getZF())+"%");
                tvPercent.setBackgroundResource(R.drawable.log_bt_log_in_bgsd);
            } else {
                tvPercent.setBackgroundResource(R.drawable.log_bt_log_in_bgsds);
                tvPercent.setText( String.format("%.2f",data.getZF())+"%");
            }

        }
    }


    public void setList(List<ExchangEreal> mDataList) {
        //  如果不行就把下方注释打开

        notifyDataSetChanged();
    }
}