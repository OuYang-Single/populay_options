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

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerMineFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.MineFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForexActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;

public class MineFragment extends BaseFragment<MineFragmentPresenter> implements MineFragmentContract.View{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getLayoutInflater().inflate(R.layout.fragment_mine, null, false);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.Mine);
    }

    @OnClick({R.id.mine_assets,R.id.mine_top_brokers,R.id.mine_forex_videos,R.id.mine_most_famous_forex_traders,R.id.mine_study_forex,R.id.mine_tools,R.id.mine_customer_service})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.mine_assets:
                ARouter.getInstance().build("/analogDisk/position").navigation();
                break;
            case R.id.mine_top_brokers:
                ARouter.getInstance().build("/analogDisk/brokers").navigation();
                break;
            case R.id.mine_forex_videos:
                ARouter.getInstance().build("/analogDisk/videos").navigation();
                break;
            case R.id.mine_most_famous_forex_traders:
                ARouter.getInstance().build("/analogDisk/Traders").navigation();
                break;
            case R.id.mine_study_forex:
              //  ARouter.getInstance().build("/analogDisk/study_forex").navigation();
                intent=new Intent(getContext(), ForexActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.mine_tools:
                ARouter.getInstance().build("/analogDisk/tools").navigation();
                break;
            case R.id.mine_customer_service:
                ARouter.getInstance().build("/analogDisk/customer_service").navigation();
                break;
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
