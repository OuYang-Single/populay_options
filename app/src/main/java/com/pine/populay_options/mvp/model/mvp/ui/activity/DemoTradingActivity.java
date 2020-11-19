package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.stockChart.KLineChart;
import com.github.mikephil.charting.stockChart.dataManage.KLineDataManage;
import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.DateUtil.timeStamp2Date;

public class DemoTradingActivity  extends BaseActivity<DemoTradingPresenter> implements DemoTradingContract.View, IActivity, ActivityLifecycleable {
    @BindView(R.id.combinedchart)
    KLineChart mCombinedchart;
    @BindView(R.id.toolbar_title)
    TextView tvToolbar;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_high)
    TextView tvHigh;
    @BindView(R.id.tv_low)
    TextView tvLow;
    @BindView(R.id.tv_vol)
    TextView tvVol;
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    @BindView(R.id.tv_percents)
    TextView tvPercents;
    List<KLineDataModel> mExchangeChart;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDemoTradingComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        return R.layout.activity_demo_trading; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
         tvToolbar.setText("XAU");
        mCombinedchart.  initChart(false);
        mPresenter.timeLoop("XAU");
        mPresenter. onChartData("1M","XAU","1");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onCharData(List<KLineDataModel> mExchangeChart,String pairs,  String type) {
        KLineDataManage   kLineData = new KLineDataManage(this);
        kLineData.parseKlineData(mExchangeChart,pairs,false,type);
        mCombinedchart.setDataToChart(kLineData);
       this. mExchangeChart=mExchangeChart;

    }


    @Override
    public void getOffer(ExchangEreal exchangEreal) {
        if (mExchangeChart!=null){
            String NEW= exchangEreal.getM1();
            String[] NewData=  NEW.split(",");//秒时间戳,开盘价,最高价,最低价,量
            KLineDataManage   kLineData = new KLineDataManage(this);
            List<KLineDataModel> modelList=new ArrayList<>();
            KLineDataModel model=new KLineDataModel();
            model.setDateMills(timeStamp2Date(Long.parseLong(NewData[0])));
            model.setOpen(Double.parseDouble(NewData[1]));
            model.setHigh(Double.parseDouble(NewData[2]));
            model.setLow(Double.parseDouble(NewData[3]));
            model.setVolume(Double.parseDouble(NewData[4]));
            model.setClose(exchangEreal.getP());
            model.setTick(Long.parseLong(NewData[0]));

            modelList.add(model);
            if (Long.parseLong(NewData[0]) == mExchangeChart.get(mExchangeChart.size() - 1).getTick()){
                kLineData.parseKlineData(modelList,"XAU",false,"5M");
                mCombinedchart.dynamicsUpdateOne(kLineData);
            }else {
                mExchangeChart.add(model);
                kLineData.parseKlineData(modelList,"XAU",false,"5M");
                mCombinedchart.dynamicsAddOne(kLineData);
            }


        }

      //  Log.wtf("ExchangEreal",exchangEreal.getBid()+"");
        tvPrice.setText(exchangEreal.getP()+"");
        tvHigh.setText(exchangEreal.getH()+"");
        tvLow.setText(exchangEreal.getL()+"");
        tvVol.setText(exchangEreal.getNV()+"");
        tvPercent.setText( String.format("%.3f", exchangEreal.getP()-exchangEreal.getYC())+"");
        tvPercents.setText( String.format("%.2f",exchangEreal.getZF())+"%");

    }
}