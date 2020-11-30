package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.model.AddDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.DetailsModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AddDetailsModule {
    @Binds
    abstract AddDetailsContract.Model bindTopicsFragmentModel(AddDetailsModel model);

}
