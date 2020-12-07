package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.MineFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PaperFragmentModel;

import dagger.Binds;
import dagger.Module;

@Module
public  abstract class MineFragmentModule {
    @Binds
    abstract MineFragmentContract.Model bindMainModel(MineFragmentModel model);

}
