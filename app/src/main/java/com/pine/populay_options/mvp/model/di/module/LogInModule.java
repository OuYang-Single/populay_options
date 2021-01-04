package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.contract.RegisteredContract;
import com.pine.populay_options.mvp.model.mvp.model.LogInModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class LogInModule {

    @Binds
    abstract LogInContract.Model bindMainModel(LogInModel model);
    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }

    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(LogInContract.View view){
        return new PromptDialog(view.getActivity());
    }
}