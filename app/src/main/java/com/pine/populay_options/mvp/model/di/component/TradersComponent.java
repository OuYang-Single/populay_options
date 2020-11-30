package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.AddDetailsModule;
import com.pine.populay_options.mvp.model.di.module.TradersModule;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.TradersActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = TradersModule.class, dependencies = AppComponent.class)
public interface TradersComponent {
    void inject(TradersActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TradersComponent.Builder view(TradersContract.View view);

        TradersComponent.Builder appComponent(AppComponent appComponent);

        TradersComponent build();
    }
}
