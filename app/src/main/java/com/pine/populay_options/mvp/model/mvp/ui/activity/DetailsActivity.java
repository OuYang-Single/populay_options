package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.MainPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsContract.View{
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.contet)
    TextView contet;
    @BindView(R.id.chart_text)
    TextView chart_text;
    @Inject
    DetailsAdapter mDetailsAdapter;
    Topics mTopics;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
           return R.layout.activity_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTopics=getIntent().getParcelableExtra("Topics");
        tv_name.setText(mTopics.getTitle());
        contet.setText(mTopics.getContent());
        String dateFormat = "d MMMM,EEEE";
        DateFormat.format(dateFormat, new Date().getTime()).toString();

        Calendar c = Calendar.getInstance();
        long time=  c.getTime().getTime()-mTopics.getCreateTime();
        long ss=(time)/(1000); //共计秒数
        int MM = (int)ss/60;   //共计分钟数
        int hh=(int)ss/3600;  //共计小时数
        int dd=(int)hh/24;   //共计天数
        if (dd==0){
            if (hh==0){
                if (MM==0){
                    chart_text.setText( ss+" second ago");
                }else {
                    chart_text.setText( MM+" minute ago");
                }
            }else {
                chart_text.setText( hh+" hour ago");
            }
        }else {
            chart_text.setText( dd+" days ago");
        }
      // chart_text.setText( dateFormats);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDetailsAdapter);
        mToolbarBack.setVisibility(View.VISIBLE);
        mPresenter.initData();
        setTitle(R.string.details);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
