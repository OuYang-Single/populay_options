package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.ForgetPasswordModule;
import com.pine.populay_options.mvp.model.di.module.HomeFragmentModule;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.HomeFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForgetPasswordActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.HomeFragment;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = HomeFragmentModule.class, dependencies = AppComponent.class)
public interface HomeFragmentComponent {
    void inject(HomeFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeFragmentComponent.Builder view(HomeFragmentContract.View view);

        HomeFragmentComponent.Builder appComponent(AppComponent appComponent);

        HomeFragmentComponent build();
    }
}