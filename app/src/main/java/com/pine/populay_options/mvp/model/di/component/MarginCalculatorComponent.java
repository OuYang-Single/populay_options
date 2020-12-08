package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.AddDetailsModule;
import com.pine.populay_options.mvp.model.di.module.MarginCalculatorModule;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.MarginCalculatorContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MarginCalculatorActivity;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = MarginCalculatorModule.class, dependencies = AppComponent.class)
public interface MarginCalculatorComponent {
    void inject(MarginCalculatorActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MarginCalculatorComponent.Builder view(MarginCalculatorContract.View view);

        MarginCalculatorComponent.Builder appComponent(AppComponent appComponent);

        MarginCalculatorComponent build();
    }
}