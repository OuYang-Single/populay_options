package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Brokers;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.mvp.contract.BrokersContract;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.BrokersModel;
import com.pine.populay_options.mvp.model.mvp.model.MineFragmentModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public  abstract class BrokersModule {
    @Binds
    abstract BrokersContract.Model bindMainModel(BrokersModel model);

    @ActivityScope
    @Provides
    public static List<Brokers> getBrokersList(){
        return new ArrayList<>();
    }

}
