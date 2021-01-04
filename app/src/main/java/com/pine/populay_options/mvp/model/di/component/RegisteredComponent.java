package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.QuotesFragmentModule;
import com.pine.populay_options.mvp.model.di.module.RegisteredModule;
import com.pine.populay_options.mvp.model.mvp.contract.QuotesFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.RegisteredContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.RegisteredActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.QuotesFragment;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = RegisteredModule.class, dependencies = AppComponent.class)
public interface RegisteredComponent {
    void inject(RegisteredActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        RegisteredComponent.Builder view(RegisteredContract.View view);

        RegisteredComponent.Builder appComponent(AppComponent appComponent);

        RegisteredComponent build();
    }
}
