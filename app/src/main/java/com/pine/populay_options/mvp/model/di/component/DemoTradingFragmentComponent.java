package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.DemoTradingFragmentModule;
import com.pine.populay_options.mvp.model.di.module.DemoTradingModule;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DemoTradingActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.DemoTradingFragment;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = DemoTradingFragmentModule.class, dependencies = AppComponent.class)
public interface DemoTradingFragmentComponent {
    void inject(DemoTradingFragment activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        DemoTradingFragmentComponent.Builder view(DemoTradingFragmentContract.View view);

        DemoTradingFragmentComponent.Builder appComponent(AppComponent appComponent);

        DemoTradingFragmentComponent build();
    }
}
