package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.MarginCalculatorContract;
import com.pine.populay_options.mvp.model.mvp.model.AddDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.MarginCalculatorModel;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class MarginCalculatorModule {
    @Binds
    abstract MarginCalculatorContract.Model bindMarginCalculatorModel(MarginCalculatorModel model);

}
