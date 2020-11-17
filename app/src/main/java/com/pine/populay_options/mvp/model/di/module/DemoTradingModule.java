package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.model.DemoTradingModel;
import com.pine.populay_options.mvp.model.mvp.model.LogInModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DemoTradingModule {
    @Binds
    abstract DemoTradingContract.Model bindMainModel(DemoTradingModel model);
}
