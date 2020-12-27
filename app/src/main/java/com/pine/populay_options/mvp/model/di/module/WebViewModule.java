package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;
import com.pine.populay_options.mvp.model.mvp.model.VideoModel;
import com.pine.populay_options.mvp.model.mvp.model.WebViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WebViewModule {
    @Binds
    abstract WebViewContract.Model bindWebViewContractModel(WebViewModel model);
}
