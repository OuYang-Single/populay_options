package com.pine.populay_options.mvp.model.di.component;

import com.pine.populay_options.mvp.model.di.module.LogInModule;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;


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
@ActivityScope
@Component(modules = LogInModule.class, dependencies = AppComponent.class)
public interface LogInComponent {
    void inject(LogInActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LogInComponent.Builder view(LogInContract.View view);

        LogInComponent.Builder appComponent(AppComponent appComponent);

        LogInComponent build();
    }
}