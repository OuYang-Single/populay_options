package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.stockChart.KLineChart;
import com.github.mikephil.charting.stockChart.dataManage.KLineDataManage;
import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.kareluo.ui.OptionMenu;
import me.kareluo.ui.PopupMenuView;
import me.kareluo.ui.PopupView;

import static android.widget.LinearLayout.VERTICAL;
import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.DateUtil.timeStamp2Date;

public class DemoTradingFragment extends BaseFragment<DemoTradingFragmentPresenter> implements DemoTradingFragmentContract.View {
    @BindView(R.id.line_chart)
    KLineChart mCombinedchart;
    private String mType;//
    private String symbol;//
    String urlNam;
    private boolean land;//是否横屏
    List<KLineDataModel> mExchangeChart;
    public static DemoTradingFragment newInstance(String type,boolean land,String symbol){
        DemoTradingFragment fragment = new DemoTradingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putBoolean("landscape",land);
        bundle.putString("symbol",symbol);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDemoTradingFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getLayoutInflater().inflate(R.layout.fragment_demo_trading, null, false);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mType = getArguments().getString("type");
        land = getArguments().getBoolean("landscape");
        symbol = getArguments().getString("symbol");
        mCombinedchart.  initChart(land);
        mPresenter. onChartData(mType,symbol,"1",1);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void onCharData(List<KLineDataModel> mExchangeChart, String pairs, String urlNam, int type) {
            this. urlNam=urlNam;
            KLineDataManage   kLineData = new KLineDataManage(getContext());
            kLineData.parseKlineData(mExchangeChart,pairs,false,urlNam);
            mCombinedchart.setDataToChart(kLineData);
            this. mExchangeChart=mExchangeChart;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onExchangErealEvent(ExchangEreal exchangEreal) {
        if (mCombinedchart!=null){
            if (getContext()!=null){
                String NEW= exchangEreal.getM1();
                switch (mType){
                    case  "1M":
                        exchangEreal.getM1();
                        break;
                    case "5M":
                        exchangEreal.getM5();
                        break;
                    case "10M":
                        exchangEreal.getM10();
                        break;
                    case "15M":
                        exchangEreal.getM15();
                        break;
                    case "30M":
                        exchangEreal.getM30();
                        break;
                    case "1H":
                        exchangEreal.getH1();
                        break;
                    case  "2H":
                        exchangEreal.getH2();
                        break;
                    case "4H":
                        exchangEreal.getH4();
                        break;
                    case  "D":
                        exchangEreal.getD();
                        break;
                }
                String[] NewData=  NEW.split(",");//秒时间戳,开盘价,最高价,最低价,量
                KLineDataManage   kLineData = new KLineDataManage(getContext());
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
                    kLineData.parseKlineData(modelList,"",false,urlNam);
                    mCombinedchart.dynamicsUpdateOne(kLineData);
                }else {
                    mExchangeChart.add(model);
                    kLineData.parseKlineData(modelList,"",false,urlNam);
                    mCombinedchart.dynamicsAddOne(kLineData);
                }
            }
        }
    }
    @Override
    public void getOffer(ExchangEreal exchangEreal) {


    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}