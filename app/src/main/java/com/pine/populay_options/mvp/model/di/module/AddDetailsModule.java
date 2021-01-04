package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.model.AddDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.DetailsModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;

@Module
public abstract class AddDetailsModule {
    @Binds
    abstract AddDetailsContract.Model bindTopicsFragmentModel(AddDetailsModel model);
    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }

    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(AddDetailsContract.View view){
        return new PromptDialog(view.getActivity());
    }
}
