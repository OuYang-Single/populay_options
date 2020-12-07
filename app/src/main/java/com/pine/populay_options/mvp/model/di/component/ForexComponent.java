package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.CustomerModule;
import com.pine.populay_options.mvp.model.di.module.ForexModule;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.CustomerActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForexActivity;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = ForexModule.class, dependencies = AppComponent.class)
public interface ForexComponent {
    void inject(ForexActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ForexComponent.Builder view(ForexContract.View view);

        ForexComponent.Builder appComponent(AppComponent appComponent);

        ForexComponent build();
    }
}
