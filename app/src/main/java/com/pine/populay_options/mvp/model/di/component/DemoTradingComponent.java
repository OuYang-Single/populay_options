package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.DemoTradingModule;
import com.pine.populay_options.mvp.model.di.module.LogInModule;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DemoTradingActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = DemoTradingModule.class, dependencies = AppComponent.class)
public interface DemoTradingComponent {
    void inject(DemoTradingActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        DemoTradingComponent.Builder view( DemoTradingContract.View view);

        DemoTradingComponent.Builder appComponent(AppComponent appComponent);

        DemoTradingComponent build();
    }
}
