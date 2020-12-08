package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.CurrencyConverterContract;
import com.pine.populay_options.mvp.model.mvp.contract.MarginCalculatorContract;
import com.pine.populay_options.mvp.model.mvp.model.CurrencyConverterModel;
import com.pine.populay_options.mvp.model.mvp.model.MarginCalculatorModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CurrencyConverterModule {
    @Binds
    abstract CurrencyConverterContract.Model bindCurrencyConverterModel(CurrencyConverterModel model);

}
