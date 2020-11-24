package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.model.PaperFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PaperFragmentModule {
    @Binds
    abstract PaperFragmentContract.Model bindMainModel(PaperFragmentModel model);
}
