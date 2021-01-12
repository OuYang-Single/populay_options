package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.MineFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PaperFragmentModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;

@Module
public  abstract class MineFragmentModule {
    @Binds
    abstract MineFragmentContract.Model bindMainModel(MineFragmentModel model);
    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }


    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(MineFragmentContract.View view){
        return new PromptDialog(view.getActivity());
    }
}
