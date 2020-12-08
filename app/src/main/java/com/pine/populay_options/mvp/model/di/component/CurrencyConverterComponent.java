package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.CurrencyConverterModule;
import com.pine.populay_options.mvp.model.di.module.MarginCalculatorModule;
import com.pine.populay_options.mvp.model.mvp.contract.CurrencyConverterContract;
import com.pine.populay_options.mvp.model.mvp.contract.MarginCalculatorContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.CurrencyConverterActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MarginCalculatorActivity;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = CurrencyConverterModule.class, dependencies = AppComponent.class)
public interface CurrencyConverterComponent {
    void inject(CurrencyConverterActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CurrencyConverterComponent.Builder view(CurrencyConverterContract.View view);

        CurrencyConverterComponent.Builder appComponent(AppComponent appComponent);

        CurrencyConverterComponent build();
    }
}