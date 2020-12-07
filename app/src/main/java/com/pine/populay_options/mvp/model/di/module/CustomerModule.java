package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.model.CustomerModel;
import com.pine.populay_options.mvp.model.mvp.model.VideoModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CustomerModule {
    @Binds
    abstract CustomerContract.Model bindCustomerContractModel(CustomerModel model);


}
