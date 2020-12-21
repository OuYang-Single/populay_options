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
import com.jess.arms.http.imageloader.ImageLoader;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerCustomerComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerVideoComponent;
import com.pine.populay_options.mvp.model.entity.Message;
import com.pine.populay_options.mvp.model.entity.Users;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.presenter.CustomerPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.VideoPresenter;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessageInput;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessagesList;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessagesListAdapter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.pine.populay_options.app.utils.ResourcesUtils.getString;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;


@Route(path = "/analogDisk/customer_service")
public class CustomerActivity extends BaseActivity<CustomerPresenter> implements CustomerContract.View,  MessageInput.InputListener {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.recycler_customer)
    MessagesList mRecyclerCustomer;
    @BindView(R.id.message_input)
    MessageInput mMessageInput;
    @Inject
    MessagesListAdapter adapter;
    @Inject
    List<Message> messages;
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
        mRecyclerCustomer.setAdapter(adapter,true);

        mMessageInput.setInputListener(this);
        mToolbarBack.setVisibility(View.VISIBLE);
        mPresenter.initData();


    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @Override
    public boolean onSubmit(CharSequence input) {
        Message message=new Message();
        Users users=new Users();
        users.setId("213213");
        users.setAvatars(R.mipmap.ic_avatar);
        message.setUser(users);
        message.setId("213213");
        message.setCreatedAt(new Date());
        message.setText(input.toString());
        adapter.addToStart(message,true);
        messages.add(message);
        message=new Message();
        users=new Users();
        users.setId(21+"");
        users.setAvatars(R.mipmap.ic_avatar);
        message.setUser(users);
        message.setId(21+"");
        message.setCreatedAt(new Date());
        message.setText("Service busy. Please wait a second...");
        adapter.addToStart(message,true);
        messages.add(message);
        //mRecyclerCustomer.scrollToPosition(messages.size()-1);
        return false;
    }
}
