package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.DetailsModule;
import com.pine.populay_options.mvp.model.di.module.LogInModule;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = DetailsModule.class, dependencies = AppComponent.class)
public  interface DetailsComponent {
    void inject(DetailsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        DetailsComponent.Builder view(DetailsContract.View view);

        DetailsComponent.Builder appComponent(AppComponent appComponent);

        DetailsComponent build();
    }
}
