package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.AddDetailsModule;
import com.pine.populay_options.mvp.model.di.module.DemoTradingModule;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = AddDetailsModule.class, dependencies = AppComponent.class)
public interface AddDetailsComponent {
    void inject(AddDetailsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AddDetailsComponent.Builder view(AddDetailsContract.View view);

        AddDetailsComponent.Builder appComponent(AppComponent appComponent);

        AddDetailsComponent build();
    }
}