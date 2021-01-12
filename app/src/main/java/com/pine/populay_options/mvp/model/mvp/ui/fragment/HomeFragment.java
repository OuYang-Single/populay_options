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
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.mvp.model.di.component.DaggerHomeFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerMineFragmentComponent;
import com.pine.populay_options.mvp.model.entity.AdEntity;
import com.pine.populay_options.mvp.model.entity.BandItem;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.entity.HomeEvent;
import com.pine.populay_options.mvp.model.entity.HomeEvents;
import com.pine.populay_options.mvp.model.mvp.contract.HomeFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.HomeFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.TopicsFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.RegisteredActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WebViewActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeContentAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;

public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentContract.View {
 /*   @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;*/
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Inject
    List<BandItem> mBandItemList;
    @Inject
    HomeContentAdapter mHomeContentAdapter;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
//
    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_home, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.title_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mHomeContentAdapter);
        mPresenter.initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(HomeEvent message) {
        if( mHomeContentAdapter.getInfos().size()>0){
            BandItem<List<ExchangEreal>, HomeItemAdPagerAdapter> list= mBandItemList.get(1);
            list.setData(message.erealList);
            mHomeContentAdapter.notifyItemChanged(1);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(HomeEvents message) {
        if( mHomeContentAdapter.getInfos().size()>0){
            BandItem<List<ExchangEreal>, HomeItemAdapter> list= mBandItemList.get(2);
            list.getAdapter().getInfos().clear();
            list.getAdapter().getInfos().addAll(message.erealList);
            mHomeContentAdapter.notifyItemChanged(2);
        }
    }
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onClick(View view, AdEntity adEntity, int position) {
        String url=adEntity.getWebsiteAddress();
        Intent intent=new Intent(getContext(), WebViewActivity.class);
        intent.putExtra("type",3);
        intent.putExtra("URL",url);
        startActivity(intent);
    }
}
