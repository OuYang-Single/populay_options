package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.stockChart.KLineChart;
import com.github.mikephil.charting.stockChart.dataManage.KLineDataManage;
import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.di.component.DaggerDemoTradingComponent;
import com.pine.populay_options.mvp.model.entity.KSelectBean;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.presenter.DemoTradingPresenter;;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.DemoTradingFragment;
import com.pine.populay_options.mvp.model.wigth.CustomDialog;
import com.pine.populay_options.mvp.model.wigth.NoScrollViewPager;
import com.volley.library.flowtag.FlowTagLayout;
import com.volley.library.flowtag.adapter.BaseFlowAdapter;
import com.volley.library.flowtag.adapter.BaseTagHolder;


import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import m.zz.zzlibrary.widget.BottomShareMenu;
import me.kareluo.ui.OptionMenu;
import me.kareluo.ui.PopupMenuView;
import me.kareluo.ui.PopupView;

import static android.widget.LinearLayout.VERTICAL;
import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.DateUtil.timeStamp2Date;

public class DemoTradingActivity  extends BaseActivity<DemoTradingPresenter> implements DemoTradingContract.View, IActivity, ActivityLifecycleable, View.OnClickListener {

    @BindView(R.id.vp_content)
    NoScrollViewPager vpContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.time)
    FrameLayout mFrameLayout;
    @BindView(R.id.tv_sell_value)
    TextView tvSellValue;
    @BindView(R.id.tv_buy_value)
    TextView tvBuyValue;
    List<KLineDataModel> mExchangeChart;
    String[] titles = {"1M", "5M", "10M", "15M", "30M", "1H", "2H", "4H", "D"};
    List<Fragment>  fragments ;
    PopupMenuView menuView;
    BottomShareMenu bottomShareMenu;
    TabLayout mButtonTabLayout;
    View mButtonView;
    TextView tv_chart_bottom_tag_view_type_1;
    TextView tv_chart_bottom_tag_view_type_2;
    TextView tv_chart_bottom_tag_view_type_3;
    TextView tv_chart_bottom_tag_view_type_4;
    TextView tv_chart_bottom_tag_view_type_5;
    TextView tv_chart_bottom_tag_view_type_6;
    TextView tv_chart_bottom_tag_view_type_7;
    TextView tv_chart_bottom_view_text;
    TextView tv_chart_bottom_view_type;
    ImageView img_chart_bottom_view_add;
    ImageView img_chart_bottom_view_minus;
    EditText edit_chart_bottom_view_price;
    TextView tv_chart_bottom_view_margin;
    TextView tv_chart_bottom_view_fee;
    TextView tv_chart_bottom_view_transaction;
    List<KSelectBean> mFlowlist;
    List<KSelectBean> mFlowlists;
    List<TextView> mTextView;
    int anInt=1;
    float fee=10.23f;
    double buy;
    double sell;
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
        toolbar.setTitle("XAU");
        mPresenter.timeLoop("XAU");
        fragments=new ArrayList<>();
        for (int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            fragments.add(DemoTradingFragment.newInstance(titles[i],false,"XAU"));
        }
        vpContent.setOffscreenPageLimit(fragments.size());
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mFlowlist=new ArrayList<>();
        mTextView=new ArrayList<>();
        mFlowlist.add(new KSelectBean("1",true));
        mFlowlist.add(new KSelectBean("2",false));
        mFlowlist.add(new KSelectBean("5",false));
        mFlowlist.add(new KSelectBean("10",false));
        mFlowlists=new ArrayList<>();
        mFlowlists.add(new KSelectBean("100",true));
        mFlowlists.add(new KSelectBean("160",false));
        mFlowlists.add(new KSelectBean("240",false));
        tabLayout.setupWithViewPager(vpContent);
        menuView=new PopupMenuView(this);
        menuView.setMenuItems(Arrays.asList(
                new OptionMenu("1m"), new OptionMenu("5M"),
                new OptionMenu("10M"), new OptionMenu("15M"),
                new OptionMenu("30M"), new OptionMenu("1H"), new OptionMenu("2H"), new OptionMenu("4H"), new OptionMenu("D")));
        menuView.setSites(PopupView.SITE_BOTTOM);
        menuView.setOrientation(VERTICAL);//vertical
         bottomShareMenu=new BottomShareMenu(this) {

            @Override
            protected View onBindView() {
                mButtonView=   getLayoutInflater().inflate(R.layout.bottom_view,null);
                return mButtonView;
            }

            @Override
            protected void setData() {
                mButtonTabLayout=(TabLayout)   mButtonView.findViewById(R.id.chart_bottom_view_tabs);
                tv_chart_bottom_view_text=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_view_text);
                tv_chart_bottom_tag_view_type_1=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_1);
                tv_chart_bottom_tag_view_type_2=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_2);
                tv_chart_bottom_tag_view_type_3=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_3);
                tv_chart_bottom_tag_view_type_4=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_4);
                tv_chart_bottom_tag_view_type_5=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_5);
                tv_chart_bottom_tag_view_type_6=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_6);
                tv_chart_bottom_tag_view_type_7=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_tag_view_type_7);
                tv_chart_bottom_view_type=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_view_type);
                tv_chart_bottom_tag_view_type_1.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_2.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_3.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_4.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_5.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_6.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_tag_view_type_7.setOnClickListener(DemoTradingActivity.this);
                setTextVIew(tv_chart_bottom_tag_view_type_1,true);
                setTextVIew(tv_chart_bottom_tag_view_type_5,true);
                tv_chart_bottom_view_transaction=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_view_transaction);
                tv_chart_bottom_view_fee=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_view_fee);
                //tv_chart_bottom_view_margin=(TextView)   mButtonView.findViewById(R.id.tv_chart_bottom_view_margin);
                edit_chart_bottom_view_price=(EditText)   mButtonView.findViewById(R.id.edit_chart_bottom_view_price);
                img_chart_bottom_view_minus=(ImageView)   mButtonView.findViewById(R.id.img_chart_bottom_view_minus);
                img_chart_bottom_view_add=(ImageView)   mButtonView.findViewById(R.id.img_chart_bottom_view_add);
                mButtonTabLayout.addTab(mButtonTabLayout.newTab().setText("Market"));
                mButtonTabLayout.addTab(mButtonTabLayout.newTab().setText("Order"));
                mButtonView.findViewById(R.id.ilayout_chart_bottom_view_price).setVisibility(View.GONE);
                mButtonTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (  tab.getText().equals(("Market"))) {
                            mButtonView.findViewById(R.id.ilayout_chart_bottom_view_price).setVisibility(View.GONE);
                        }else {
                            mButtonView.findViewById(R.id.ilayout_chart_bottom_view_price).setVisibility(View.VISIBLE);
                            edit_chart_bottom_view_price.setText(tvPrice.getText());
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
                img_chart_bottom_view_minus.setOnClickListener(DemoTradingActivity.this);
                img_chart_bottom_view_add.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_view_transaction.setOnClickListener(DemoTradingActivity.this);
                tv_chart_bottom_view_text.setText("XAU");


            }
        };
     //   bottomShareMenu.getWindow().setLayout(100,200);

    }
    @OnClick({R.id.time,R.id.llayout_sell,R.id.llayout_buy})
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.time:
                 menuView.show(mFrameLayout);
                break;
            case R.id.llayout_sell:
                anInt=1;
                tv_chart_bottom_view_type.setText(getString(R.string.sell));
                tv_chart_bottom_view_type.setTextColor(ResourcesUtils.getColor(this,R.color.white));
                tv_chart_bottom_view_type.setBackgroundResource(R.drawable.chart_bottom_view_sell);
                bottomShareMenu.show();//显示
                tv_chart_bottom_view_transaction.setText(tv_chart_bottom_view_type.getText()+" "+buy);
                break;
            case R.id.llayout_buy:
                anInt=2;
                tv_chart_bottom_view_type.setText(getString(R.string.buy));
                tv_chart_bottom_view_type.setTextColor(ResourcesUtils.getColor(this,R.color.chart_text_minor));
                tv_chart_bottom_view_type.setBackgroundResource(R.drawable.chart_bottom_view_buy);
                tv_chart_bottom_view_transaction.setText(tv_chart_bottom_view_type.getText()+" "+sell);
                bottomShareMenu.show();//显示
                break;
        }
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
    public void onCharData(List<KLineDataModel> mExchangeChart,String pairs,  String type,int types) {
        this. mExchangeChart=mExchangeChart;
        KLineDataManage   kLineData = new KLineDataManage(this);
        kLineData.parseKlineData(mExchangeChart,pairs,false,type);
        EventBus.getDefault().postSticky(kLineData);
    }


    @Override
    public void getOffer(ExchangEreal exchangEreal) {
        tvPrice.setText(String.format("%.2f",exchangEreal.getP()));
        tvHigh.setText(exchangEreal.getH() +"");
        tvLow.setText(exchangEreal.getL()+"");
        tvVol.setText(exchangEreal.getNV() +"");
        if (mExchangeChart!=null){
            if (mExchangeChart.get(mExchangeChart.size()-1).getClose()-exchangEreal.getP()<0){
                tvPrice.setTextColor(ResourcesUtils.getColor(this,R.color.chart_green));
            }else {
                tvPrice.setTextColor(ResourcesUtils.getColor(this,R.color.chart_red));
            }
        }
        tvSellValue.setText(exchangEreal.getS1()+"");
        tvBuyValue.setText(exchangEreal.getB1()+"");
        if (exchangEreal.getZF()>0){
            tvPercent.setText( "+"+String.format("%.5f", exchangEreal.getP()-exchangEreal.getYC())+"");
            tvPercents.setText("+"+ String.format("%.2f",exchangEreal.getZF())+"%");
            tvPercent.setTextColor(ResourcesUtils.getColor(this,R.color.chart_green));
            tvPercents.setTextColor(ResourcesUtils.getColor(this,R.color.chart_green));
        }else {
            tvPercent.setText( String.format("%.5f", exchangEreal.getP()-exchangEreal.getYC())+"");
            tvPercents.setText( String.format("%.2f",exchangEreal.getZF())+"%");
            tvPercent.setTextColor(ResourcesUtils.getColor(this,R.color.chart_red));
            tvPercents.setTextColor(ResourcesUtils.getColor(this,R.color.chart_red));
        }
        buy=exchangEreal.getB1();
        sell=exchangEreal.getS1();
        if (tv_chart_bottom_view_transaction!=null){

            if (anInt==1){
                tv_chart_bottom_view_transaction.setText(tv_chart_bottom_view_type.getText()+" "+exchangEreal.getS1());
            }else {
                tv_chart_bottom_view_transaction.setText(tv_chart_bottom_view_type.getText()+" "+exchangEreal.getB1());
            }

        }

        if (mExchangeChart!=null){

            EventBus.getDefault().postSticky(String.format("%.2f",exchangEreal.getP()));

        }
    }


    @Override
    public void onClick(View v) {


        float sun;
          switch (v.getId()){
            case R.id.img_chart_bottom_view_minus:

                float suks=   Float.parseFloat(edit_chart_bottom_view_price.getText().toString())+1F;
                edit_chart_bottom_view_price.setText(suks+"");
                break;
            case R.id.img_chart_bottom_view_add:
                float suk=   Float.parseFloat(edit_chart_bottom_view_price.getText().toString())-1F;
                if ( suk<0){
                    suk=0;
                }
                edit_chart_bottom_view_price.setText(suk+"");
                break;
            case R.id.tv_chart_bottom_tag_view_type_1:
                for (KSelectBean itme:mFlowlist){
                    itme.setChecked(false);
                }
                setTextVIew(tv_chart_bottom_tag_view_type_1,true);
                setTextVIew(tv_chart_bottom_tag_view_type_2,false);
                setTextVIew(tv_chart_bottom_tag_view_type_3,false);
                setTextVIew(tv_chart_bottom_tag_view_type_4,false);
                mFlowlist.get(0).setChecked(true);
                sun= fee*1f;
                tv_chart_bottom_view_fee.setText(sun+"");
                break;
            case R.id.tv_chart_bottom_tag_view_type_2:
                for (KSelectBean itme:mFlowlist){
                    itme.setChecked(false);
                }
                setTextVIew(tv_chart_bottom_tag_view_type_1,false);
                setTextVIew(tv_chart_bottom_tag_view_type_2,true);
                setTextVIew(tv_chart_bottom_tag_view_type_3,false);
                setTextVIew(tv_chart_bottom_tag_view_type_4,false);
                mFlowlist.get(1).setChecked(true);
                 sun= fee*2f;
                tv_chart_bottom_view_fee.setText(sun+"");
                break;
            case R.id.tv_chart_bottom_tag_view_type_3:
                for (KSelectBean itme:mFlowlist){
                    itme.setChecked(false);
                }
                setTextVIew(tv_chart_bottom_tag_view_type_1,false);
                setTextVIew(tv_chart_bottom_tag_view_type_2,false);
                setTextVIew(tv_chart_bottom_tag_view_type_3,true);
                setTextVIew(tv_chart_bottom_tag_view_type_4,false);
                mFlowlist.get(2).setChecked(true);
                sun= fee*5f;
                tv_chart_bottom_view_fee.setText( String.format("%.2f",sun));
                break;
            case R.id.tv_chart_bottom_tag_view_type_4:
                for (KSelectBean itme:mFlowlist){
                    itme.setChecked(false);
                }
                setTextVIew(tv_chart_bottom_tag_view_type_1,false);
                setTextVIew(tv_chart_bottom_tag_view_type_2,false);
                setTextVIew(tv_chart_bottom_tag_view_type_3,false);
                setTextVIew(tv_chart_bottom_tag_view_type_4,true);
                mFlowlist.get(3).setChecked(true);
                sun= fee*10f;
                tv_chart_bottom_view_fee.setText(  String.format("%.2f",sun)+"");
                break;
            case R.id.tv_chart_bottom_tag_view_type_5:
                mFlowlists.get(0).setChecked(true);
                setTextVIew(tv_chart_bottom_tag_view_type_5,true);
                setTextVIew(tv_chart_bottom_tag_view_type_6,false);
                setTextVIew(tv_chart_bottom_tag_view_type_7,false);
                break;
            case R.id.tv_chart_bottom_tag_view_type_6:
                mFlowlists.get(1).setChecked(true);
                setTextVIew(tv_chart_bottom_tag_view_type_5,false);
                setTextVIew(tv_chart_bottom_tag_view_type_6,true);
                setTextVIew(tv_chart_bottom_tag_view_type_7,false);
                break;
            case R.id.tv_chart_bottom_tag_view_type_7:
                mFlowlists.get(2).setChecked(true);
                setTextVIew(tv_chart_bottom_tag_view_type_5,false);
                setTextVIew(tv_chart_bottom_tag_view_type_6,false);
                setTextVIew(tv_chart_bottom_tag_view_type_7,true);
                break;
              case R.id.tv_chart_bottom_view_transaction:

                  //点击弹出对话框
                  final CustomDialog customDialog = new CustomDialog(this);
                  customDialog.setTitle("Success");
                  if (anInt==1){
                      customDialog.setPriceValue(sell+"");
                  }else {
                      customDialog.setPriceValue(buy+"");
                  }
                  customDialog.setLots("XAU"+"  Long");
                  KSelectBean itmes=null;
                  for (KSelectBean itme:mFlowlist){
                      if (itme.isChecked()){
                          itmes=itme;
                      }
                  }
                  if (itmes!=null){
                      customDialog.setLotsValue(itmes.getTagName()+"Lots");
                  }
                  customDialog.setYesOnclickListener("Check my paper accoun", new CustomDialog.onYesOnclickListener() {
                      @Override
                      public void onYesClick() {
                          //这里是确定的逻辑代码，别忘了点击确定后关闭对话框
                          customDialog.dismiss();
                      }
                  });

                  customDialog.show();
                  break;
        }
    }

    public void setTextVIew(TextView textVIew,boolean f){
        if (f){
            textVIew.setTextColor(ResourcesUtils.getColor(this,R.color.white));
            textVIew.setBackgroundResource(R.drawable.chart_bottom_view_sell);
        }else {
            textVIew.setTextColor(ResourcesUtils.getColor(this,R.color.chart_text_minor));
            textVIew.setBackgroundResource(R.drawable.chart_bottom_view_buy);
        }
    }
}