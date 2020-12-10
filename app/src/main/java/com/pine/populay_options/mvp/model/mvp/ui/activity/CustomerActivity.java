package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerCustomerComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideoComponent;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.presenter.CustomerPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.VideoPresenter;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessageInput;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessagesList;

import butterknife.BindView;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;


@Route(path = "/analogDisk/customer_service")
public class CustomerActivity extends BaseActivity<CustomerPresenter> implements CustomerContract.View {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.recycler_customer)
    MessagesList mRecyclerCustomer;
    @BindView(R.id.message_input)
    MessageInput mMessageInput;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCustomerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.customer_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.customer_service);
        mMessageInput.set
        mToolbarBack.setVisibility(View.VISIBLE);
        mPresenter.initData();

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
