package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.BookDetalisContract;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.model.BookDetalisModel;
import com.pine.populay_options.mvp.model.mvp.model.CustomerModel;

import dagger.Binds;
import dagger.Module;

    @Module
    public abstract class BookDetalisModule {
        @Binds
        abstract BookDetalisContract.Model bindBookDetalisContractModel(BookDetalisModel model);

    }
