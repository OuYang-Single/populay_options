package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.CustomerModule;
import com.pine.populay_options.mvp.model.di.module.VideoModule;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.CustomerActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.VideoActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = CustomerModule.class, dependencies = AppComponent.class)
public interface CustomerComponent {
    void inject(CustomerActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CustomerComponent.Builder view(CustomerContract.View view);

        CustomerComponent.Builder appComponent(AppComponent appComponent);

        CustomerComponent build();
    }
}
