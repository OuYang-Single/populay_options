package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.content.Context;
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

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerTopicsFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TopicsFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.kinst.jakub.view.SimpleStatefulLayout;
import cz.kinst.jakub.view.StatefulLayout;

import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NODATA;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;


public class TopicsFragment extends BaseFragment<TopicsFragmentPresenter> implements TopicsFragmentContract.View, DefaultAdapter.OnRecyclerViewItemClickListener, OnLoadmoreListener, OnRefreshLoadmoreListener {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.sfl_lyt_state)
    SimpleStatefulLayout sfl_lyt_state;
    @Inject
    StatefulLayout.StateController mStateController;
    @Inject
    TopicsAdapter mTopicsAdapter;
    int pageNum=1;
    TextView activity_error_text;
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
        sfl_lyt_state.setStateController(mStateController);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopicsAdapter);
        mTopicsAdapter.setOnItemClickListener(this);

        mSmartRefreshLayout.setOnRefreshLoadmoreListener(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        mSmartRefreshLayout.finishRefresh(false);
        mSmartRefreshLayout.finishLoadmore(false);
    }

    @OnClick({R.id.fab})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.fab:
                Intent mIntent=   new Intent(getContext(), AddDetailsActivity.class);
                startActivity(mIntent);
                break;
        }
    }
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void setText(TextView viewById) {
        activity_error_text=viewById;
    }

    @Override
    public void onClick(View v) {
        sfl_lyt_state.showContent();
        mSmartRefreshLayout.autoRefresh();

    }

    @Override
    public void onStart() {
        super.onStart();
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public Context getContent() {
        return getContext();
    }

    @Override
    public void dataNull() {
        mStateController.setState(NODATA);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        //int img=R.mipmap.icon_network_error;
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
            mStateController.setState(NONETWORK_ERROR);
        }else {
            mStateController.setState(ERROR);
            if (activity_error_text!=null){
                activity_error_text.setText(message.messing);
            }
        }

      /*  activity_error_img.setImageResource(img);
        activity_error_text.setText(message.messing);*/

    }
    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
       // Toast.makeText(getContext(),""+position,Toast.LENGTH_LONG).show();
        Intent mIntent=   new Intent(getContext(),DetailsActivity.class);
        mIntent.putExtra("Topics", mTopicsAdapter.getInfos().get(position));
       startActivity(mIntent);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNum++;
        mPresenter.initData(pageNum);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNum=1;
        mPresenter.initData(pageNum);
    }
}
