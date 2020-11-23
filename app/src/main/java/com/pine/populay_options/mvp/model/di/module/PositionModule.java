package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;
import com.pine.populay_options.mvp.model.mvp.model.WaitModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PositionModule {
    @Binds
    abstract PositionContract.Model bindMainModel(PositionModel model);
}
