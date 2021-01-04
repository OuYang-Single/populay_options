package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.RegisteredContract;
import com.pine.populay_options.mvp.model.mvp.contract.StudyForexContract;
import com.pine.populay_options.mvp.model.mvp.model.RegisteredModel;
import com.pine.populay_options.mvp.model.mvp.model.StudyForexModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;


@Module
public abstract class RegisteredModule {
    @Binds
    abstract RegisteredContract.Model bindTradersContractModel(RegisteredModel model);


    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog( RegisteredContract.View view){
        return new PromptDialog(view.getActivity());
    }


}