package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.BrokersModule;
import com.pine.populay_options.mvp.model.di.module.DemoTradingModule;
import com.pine.populay_options.mvp.model.mvp.contract.BrokersContract;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.BrokersActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DemoTradingActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = BrokersModule.class, dependencies = AppComponent.class)
public interface BrokersComponent {
    void inject(BrokersActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        BrokersComponent.Builder view( BrokersContract.View view);

        BrokersComponent.Builder appComponent(AppComponent appComponent);

        BrokersComponent build();
    }
}
