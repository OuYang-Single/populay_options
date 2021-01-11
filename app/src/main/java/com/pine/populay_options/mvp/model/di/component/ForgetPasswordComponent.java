package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.ForgetPasswordModule;
import com.pine.populay_options.mvp.model.di.module.LogInModule;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForgetPasswordActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = ForgetPasswordModule.class, dependencies = AppComponent.class)
public interface ForgetPasswordComponent {
    void inject(ForgetPasswordActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ForgetPasswordComponent.Builder view(ForgetPasswordContract.View view);

        ForgetPasswordComponent.Builder appComponent(AppComponent appComponent);

        ForgetPasswordComponent build();
    }
}