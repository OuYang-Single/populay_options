package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

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
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;


import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import retrofit2.Retrofit;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

public class DemoTradingActivity  extends BaseActivity<DemoTradingPresenter> implements DemoTradingContract.View, IActivity, ActivityLifecycleable {
    @BindView(R.id.combinedchart)
    KLineChart mCombinedchart;

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
        mCombinedchart.  initChart(false);
       mPresenter. onChartData("800","EURUSD","1");
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

    }
}