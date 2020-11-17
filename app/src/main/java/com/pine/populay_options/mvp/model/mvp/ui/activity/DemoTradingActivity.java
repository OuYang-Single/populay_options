package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.DataRequest;
import com.pine.populay_options.app.utils.DateUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.entity.KLineEntity;
import com.pine.populay_options.mvp.model.entity.MinuteLineEntity;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.LogInPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.KChartAdapter;
import com.pine.populay_options.mvp.model.wigth.chart.BaseKChartView;
import com.pine.populay_options.mvp.model.wigth.chart.KChartView;
import com.pine.populay_options.mvp.model.wigth.chart.MinuteChartView;
import com.pine.populay_options.mvp.model.wigth.chart.formatter.DateFormatter;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

public class DemoTradingActivity  extends BaseActivity<DemoTradingPresenter> implements DemoTradingContract.View, IActivity, ActivityLifecycleable, KChartView.KChartRefreshListener {

    @BindView(R.id.kchart_view)
    KChartView mKChartView;
    KChartAdapter   mAdapter;
    @BindView(R.id.ll_status)
    LinearLayout mLlStatus;
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
        setFullscreen(this);
        return R.layout.activity_demo_trading; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
         mAdapter = new KChartAdapter();
        mKChartView.setAdapter(mAdapter);
        mKChartView.setDateTimeFormatter(new DateFormatter());
        mKChartView.setGridRows(4);
        mKChartView.setGridColumns(4);
        mKChartView.setOnSelectedChangedListener(new BaseKChartView.OnSelectedChangedListener() {
            @Override
            public void onSelectedChanged(BaseKChartView view, Object point, int index) {
                KLineEntity data = (KLineEntity) point;
                Log.i("onSelectedChanged", "index:" + index + " closePrice:" + data.getClosePrice());
            }
        });
        mKChartView.showLoading();
        mKChartView.setRefreshListener(this);
        onLoadMoreBegin(mKChartView);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLlStatus.setVisibility(View.GONE);
            mKChartView.setGridRows(3);
            mKChartView.setGridColumns(8);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLlStatus.setVisibility(View.VISIBLE);
            mKChartView.setGridRows(4);
            mKChartView.setGridColumns(4);
        }
    }

    @Override
    public void onLoadMoreBegin(KChartView chart) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<KLineEntity> data = DataRequest.getData(DemoTradingActivity.this, mAdapter.getCount(), 500);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!data.isEmpty()) {
                    Log.i("onLoadMoreBegin", "start:" + data.get(0).getDatetime() + " stop:" + data.get(data.size() - 1).getDatetime());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //第一次加载时开始动画
                        if (mAdapter.getCount() == 0) {
                            mKChartView.startAnimation();
                        }
                        mAdapter.addFooterData(data);
                        //加载完成，还有更多数据
                        if (data.size() > 0) {
                            mKChartView.refreshComplete();
                        }
                        //加载完成，没有更多数据
                        else {
                            mKChartView.refreshEnd();
                        }
                    }
                });
            }
        }).start();
    }
}