package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class QuotesAdapter extends DefaultAdapter<ExchangEreal> {
    @Inject
    public QuotesAdapter(List<ExchangEreal> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<ExchangEreal> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.quotes_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<ExchangEreal> {
        @BindView(R.id.tv_quotes_text)
        TextView tvQuotesText;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.tv_percents)
        TextView tvPercents;
        @BindView(R.id.img_price)
        ImageView imgPrice;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull ExchangEreal data, int position) {
            tvQuotesText.setText(data.getFS());
            tvPrice.setText(data.getP()+"");
            if (data.getPAndn()==-1){
              tvPrice.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_red));
            }else if (data.getPAndn()==1){
              tvPrice.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_green));
            }else {
                tvPrice.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_highlight));
            }

            if (data.getZF()>0){
                imgPrice.setImageResource(R.mipmap.ic_price_add);
                tvPercent.setText( "+"+String.format("%.5f", data.getP()-data.getYC())+"");
                tvPercents.setText("+"+ String.format("%.2f",data.getZF())+"%");
                tvPercent.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_green));
                tvPercents.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_green));
            }else {
                imgPrice.setImageResource(R.mipmap.ic_price_less);
                tvPercent.setText( String.format("%.5f", data.getP()-data.getYC())+"");
                tvPercents.setText( String.format("%.2f",data.getZF())+"%");
                tvPercent.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_red));
                tvPercents.setTextColor(ResourcesUtils.getColor(tvPrice.getContext(),R.color.chart_red));
            }

        }
    }
    public void setList(List<ExchangEreal> mDataList) {
        //  如果不行就把下方注释打开

        notifyDataSetChanged();
    }
}