package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.pine.populay_options.mvp.model.entity.Videos;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.model.TradersModel;
import com.pine.populay_options.mvp.model.mvp.model.VideosModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class VideosModule {
    @Binds
    abstract VideosContract.Model bindTradersContractModel(VideosModel model);

    @ActivityScope
    @Provides
    public static  List<Videos>  getVideosList(){
        return new ArrayList<>();
    }


}
