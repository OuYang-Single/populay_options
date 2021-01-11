package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerPlayersFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerQuotesFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.QuotesFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.QuotesFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DemoTradingActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.PositionActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.QuotesAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;

public class QuotesFragment extends BaseFragment<QuotesFragmentPresenter> implements QuotesFragmentContract.View, View.OnClickListener, DefaultAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.recycler_quotes)
    RecyclerView mRecyclerQuotes;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Inject
    QuotesAdapter mQuotesAdapter;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerQuotesFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_quotes, null, false);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.quotes);
        mRecyclerQuotes.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerQuotes.setAdapter(mQuotesAdapter);
        mQuotesAdapter.setOnItemClickListener(this);
        mPresenter.initData();
    }



    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public void notifyDataSetChanged(List<ExchangEreal> mTopics) {
        mQuotesAdapter.setList(mTopics);
    }

    @Override
    public void onClick(View view) {
       //
     /*  Intent intent=new Intent(getContext(), DemoTradingActivity.class);
        intent.
       startActivity(intent);*/
    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {

        ARouter.getInstance().build("/analogDisk/demoTrading")
                .withParcelable("exchangEreal",mQuotesAdapter.getInfos().get(position))
                .navigation();
    }
}
