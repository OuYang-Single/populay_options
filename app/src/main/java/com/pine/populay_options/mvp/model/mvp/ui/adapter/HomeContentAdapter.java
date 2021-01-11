package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.entity.BandItem;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.entity.TransType;
import com.pine.populay_options.mvp.model.wigth.AutoScrollViewPager;
import com.pine.populay_options.mvp.model.wigth.MyTransformer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.pine.populay_options.mvp.model.entity.BandItem.CONTENT;
import static com.pine.populay_options.mvp.model.entity.BandItem.HEAD;
import static com.pine.populay_options.mvp.model.entity.BandItem.OPTION;


public class HomeContentAdapter extends DefaultAdapter<BandItem> {

    @Inject
    public HomeContentAdapter(List<BandItem> infos) {
        super(infos);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getmType();
    }

    @NonNull
    @Override
    public BaseHolder<BandItem> getHolder(@NonNull View v, int viewType) {
        BaseHolder<BandItem> bandItemBaseHolder = null;
        switch (viewType) {
            case HEAD:
                bandItemBaseHolder = new HomeItemHeadBaseHolder(v);
                break;
            case OPTION:
                bandItemBaseHolder = new HomeItemOptionBaseHolder(v);
                break;
            case CONTENT:
                bandItemBaseHolder = new HomeItemContentBaseHolder(v);
                break;
        }
        return bandItemBaseHolder;
    }

    @Override
    public int getLayoutId(int viewType) {
        int i = HEAD;
        switch (viewType) {
            case HEAD:
                i = R.layout.home_item_head;
                break;
            case OPTION:
                i = R.layout.home_item_option;
                break;
            case CONTENT:
                i = R.layout.home_item_content;
                break;
        }
        return i;
    }

    public class HomeItemHeadBaseHolder extends BaseHolder<BandItem> {
        @BindView(R.id.home_item_vp_ad)
        AutoScrollViewPager mViewPager;

        public HomeItemHeadBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
          //  mViewPager.setPageTransformer(true, MyTransformer.getMyTransformer(TransType.OVERLAP));
            mViewPager.setAdapter((HomeItemAdPagerAdapter) data.getAdapter());
            mViewPager.setAutoPlay(true);
          //  mViewPager.a
        }
    }

    public class HomeItemOptionBaseHolder extends BaseHolder<BandItem> {

        @BindView(R.id.xau_price)
        TextView xau_price;
        @BindView(R.id.xau_size)
        TextView xau_size;
        @BindView(R.id.eur_price)
        TextView eur_price;
        @BindView(R.id.eur_size)
        TextView eur_size;
        @BindView(R.id.usd_price)
        TextView usd_price;
        @BindView(R.id.usd_size)
        TextView usd_size;
        ExchangEreal exchangErealXau = null;
        ExchangEreal exchangErealUsd = null;
        ExchangEreal exchangErealEur = null;
        @OnClick({R.id.mine_most_famous_forex_traders,R.id.mine_top_brokers,R.id.mine_study_forex,R.id.usd_line,R.id.eur_line,R.id.xau_line})
        public void onClick(View view){
            switch (view.getId()){
                case R.id.mine_most_famous_forex_traders:
                    ARouter.getInstance().build("/analogDisk/Traders").navigation();
                    break;
                case R.id.mine_top_brokers:
                    ARouter.getInstance().build("/analogDisk/brokers").navigation();
                    break;
                case R.id.mine_study_forex:
                    ARouter.getInstance().build("/analogDisk/Forex").navigation();
                    break;
                case R.id.usd_line:
                    ARouter.getInstance().build("/analogDisk/demoTrading")
                            .withParcelable("exchangEreal",exchangErealUsd)
                            .navigation();
                    break;
                case R.id.eur_line:
                    ARouter.getInstance().build("/analogDisk/demoTrading")
                            .withParcelable("exchangEreal",exchangErealEur)
                            .navigation();
                    break;
                case R.id.xau_line:
                    ARouter.getInstance().build("/analogDisk/demoTrading")
                            .withParcelable("exchangEreal",exchangErealXau)
                            .navigation();
                    break;
            }

        }
        public HomeItemOptionBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
            if (data.getData() != null) {
                List<ExchangEreal> list = (List<ExchangEreal>) data.getData();

                for (ExchangEreal exchangEreal : list) {
                    if ("XAU".equals(exchangEreal.getFS())) {
                        exchangErealXau = exchangEreal;
                    }
                    if ("EURUSD".equals(exchangEreal.getFS())) {
                        exchangErealUsd = exchangEreal;
                    }
                    if ("USDJPY".equals(exchangEreal.getFS())) {
                        exchangErealEur = exchangEreal;
                    }
                }
                if (exchangErealXau != null) {
                    xau_price.setText(exchangErealXau.getP()+"");
                    if (exchangErealXau.getZF()>0){
                        xau_size.setText("+"+ String.format("%.2f",exchangErealXau.getZF())+"%");
                        xau_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                        xau_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                    }else {
                        xau_size.setText( String.format("%.2f",exchangErealXau.getZF())+"%");
                        xau_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                        xau_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                    }
                }
                if (exchangErealUsd != null) {
                    eur_price.setText(exchangErealUsd.getP()+"");
                    if (exchangErealUsd.getZF()>0){
                        eur_size.setText("+"+ String.format("%.2f",exchangErealUsd.getZF())+"%");
                        eur_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                        eur_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                    }else {
                        eur_size.setText( String.format("%.2f",exchangErealUsd.getZF())+"%");
                        eur_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                        eur_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                    }
                }
                if (exchangErealEur != null) {
                    usd_price.setText(exchangErealEur.getP()+"");
                    if (exchangErealEur.getZF()>0){
                        usd_size.setText("+"+ String.format("%.2f",exchangErealEur.getZF())+"%");
                        usd_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                        usd_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_greens));
                    }else {
                        usd_size.setText( String.format("%.2f",exchangErealEur.getZF())+"%");
                        usd_price.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                        usd_size.setTextColor(ResourcesUtils.getColor(xau_size.getContext(),R.color.chart_red));
                    }
                }
            }
        }
    }

    public class HomeItemContentBaseHolder extends BaseHolder<BandItem> {

        @BindView(R.id.home_item_context_recycler_view)
        RecyclerView mHomeItemContextRecyclerView;

        public HomeItemContentBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull BandItem data, int position) {
            HomeItemAdapter homeItemContextAdapter = (HomeItemAdapter) data.getAdapter();
            homeItemContextAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
                    ARouter.getInstance().build("/analogDisk/demoTrading")
                            .withParcelable("exchangEreal",homeItemContextAdapter.getInfos().get(position))
                            .navigation();
                }
            });
            mHomeItemContextRecyclerView.setLayoutManager(homeItemContextAdapter.getLayoutManager(mHomeItemContextRecyclerView.getContext()));
            mHomeItemContextRecyclerView.setAdapter(homeItemContextAdapter);

        }
    }
}
