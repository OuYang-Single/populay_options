package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.StudyForexContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.model.StudyForexModel;
import com.pine.populay_options.mvp.model.mvp.model.VideoModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class StudyForexModule {
    @Binds
    abstract StudyForexContract.Model bindTradersContractModel(StudyForexModel model);




}