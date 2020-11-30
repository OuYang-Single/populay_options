package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.TradersDetailsModule;
import com.pine.populay_options.mvp.model.di.module.TradersModule;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersDetailsContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.TradersActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.TradersDetailsActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = TradersDetailsModule.class, dependencies = AppComponent.class)
public interface TradersDetailsComponent {
    void inject(TradersDetailsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TradersDetailsComponent.Builder view(TradersDetailsContract.View view);

        TradersDetailsComponent.Builder appComponent(AppComponent appComponent);

        TradersDetailsComponent build();
    }
}
