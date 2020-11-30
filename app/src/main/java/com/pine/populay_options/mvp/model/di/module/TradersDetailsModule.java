package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersDetailsContract;
import com.pine.populay_options.mvp.model.mvp.model.TradersDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.TradersModel;

import dagger.Binds;
import dagger.Module;

@Module
public   abstract class TradersDetailsModule {
    @Binds
    abstract TradersDetailsContract.Model bindTradersDetailsContractModel(TradersDetailsModel model);
}
