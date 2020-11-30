package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.model.AddDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.TradersModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public   abstract class TradersModule {
    @Binds
    abstract TradersContract.Model bindTradersContractModel(TradersModel model);

    @ActivityScope
    @Provides
    public static List<TradersEntity> getTradersList(){
        return new ArrayList<>();
    }

}
