package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Videos;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.model.VideoModel;
import com.pine.populay_options.mvp.model.mvp.model.VideosModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class VideoModule {
    @Binds
    abstract VideoContract.Model bindTradersContractModel(VideoModel model);




}
