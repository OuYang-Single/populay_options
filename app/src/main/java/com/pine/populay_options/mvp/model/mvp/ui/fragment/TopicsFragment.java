package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerTopicsFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TopicsFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TopicsFragment extends BaseFragment<TopicsFragmentPresenter> implements TopicsFragmentContract.View, DefaultAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Inject
    TopicsAdapter mTopicsAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTopicsFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_topics, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.Topics);
        mTvRightToolbar.setText(R.string.new_topic);
        mTvRightToolbar.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopicsAdapter);
        mTopicsAdapter.setOnItemClickListener(this);
        mPresenter.initData();
    }

    @OnClick({R.id.toolbar_layout_right})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.toolbar_layout_right:
                Intent mIntent=   new Intent(getContext(), AddDetailsActivity.class);
                startActivity(mIntent);
                break;
        }
    }
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
       // Toast.makeText(getContext(),""+position,Toast.LENGTH_LONG).show();
        Intent mIntent=   new Intent(getContext(),DetailsActivity.class);
       startActivity(mIntent);
    }
}
