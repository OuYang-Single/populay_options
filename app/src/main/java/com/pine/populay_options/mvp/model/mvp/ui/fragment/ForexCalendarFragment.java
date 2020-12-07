package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerForexCalendarComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerMineFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;
import com.pine.populay_options.mvp.model.mvp.presenter.ForexCalendarPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ForexCalendarAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PaperAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class ForexCalendarFragment extends BaseFragment<ForexCalendarPresenter> implements ForexCalendarContract.View {
    @BindView(R.id.recycler_forex_calendar)
    RecyclerView mRecyclerForexCalendar;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.Future)
    TextView Future;
    @BindView(R.id.Previous)
    TextView Previous;
    @BindView(R.id.time)
    TextView tvTime;
    @Inject
    ForexCalendarAdapter mPaperAdapter;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerForexCalendarComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_forex_calendar, null, false);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText("Forex Calendar");
        Future.setText("Future >");
        Previous.setText("< Previous");
        mRecyclerForexCalendar.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerForexCalendar.setAdapter(mPaperAdapter);
        mPresenter.initData();

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void setTime(String time) {
        tvTime.setText(time);
    }
}
