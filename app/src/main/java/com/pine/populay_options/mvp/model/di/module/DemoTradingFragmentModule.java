package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.model.DemoTradingFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.DemoTradingModel;
import com.pine.populay_options.mvp.model.mvp.model.LogInModel;

import java.util.concurrent.TimeUnit;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.APP_DOMAIN;

@Module
public abstract class DemoTradingFragmentModule {
    @Binds
    abstract DemoTradingFragmentContract.Model bindMainModel(DemoTradingFragmentModel model);

}
