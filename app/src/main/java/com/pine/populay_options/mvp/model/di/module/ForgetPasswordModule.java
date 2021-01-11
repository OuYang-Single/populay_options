package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.model.ForexModel;
import com.pine.populay_options.mvp.model.mvp.model.ForgetPasswordModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;


@Module
public abstract class ForgetPasswordModule {
    @Binds
    abstract ForgetPasswordContract.Model bindForexContractModel(ForgetPasswordModel model);

    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(ForgetPasswordContract.View view){
        return new PromptDialog(view.getActivity());
    }

}