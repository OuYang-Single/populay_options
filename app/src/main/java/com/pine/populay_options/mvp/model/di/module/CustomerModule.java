package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Message;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.model.CustomerModel;
import com.pine.populay_options.mvp.model.mvp.model.VideoModel;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessagesListAdapter;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CustomerModule {
    @Binds
    abstract CustomerContract.Model bindCustomerContractModel(CustomerModel model);
    @ActivityScope
    @Provides
    public static List<Message> getWrapperList(){
        return new ArrayList<>();
    }
    @ActivityScope
    @Provides
    public static MessagesListAdapter getMessagesListAdapter(  ImageLoader mImageLoader){

        return new MessagesListAdapter<Message>("213213",mImageLoader);
    }


}
