package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Message;
import com.pine.populay_options.mvp.model.entity.Users;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessagesListAdapter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.pine.populay_options.app.utils.ResourcesUtils.getString;

@ActivityScope
public class CustomerModel extends BaseModel implements CustomerContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    MessagesListAdapter adapter;
    @Inject
    List<Message> messages;
    @Inject
    public CustomerModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void initData() {
        Message message=new Message();
        Users users=new Users();
        users.setId(21+"");
        users.setAvatars(R.mipmap.ic_avatar);
        message.setUser(users);
        message.setId(21+"");
        message.setCreatedAt(new Date());
        message.setText(getString(mApplication,R.string.message_text));
        messages.add(message);
         adapter.addToEnd(messages,true);
    }
}