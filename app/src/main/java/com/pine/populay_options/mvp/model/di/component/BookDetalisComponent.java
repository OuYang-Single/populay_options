package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.BookDetalisModule;
import com.pine.populay_options.mvp.model.di.module.BrokersModule;
import com.pine.populay_options.mvp.model.mvp.contract.BookDetalisContract;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.BookDetalisActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.CustomerActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = BookDetalisModule.class, dependencies = AppComponent.class)
public interface BookDetalisComponent{
    void inject(BookDetalisActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BookDetalisComponent.Builder view(BookDetalisContract.View view);

        BookDetalisComponent.Builder appComponent(AppComponent appComponent);

        BookDetalisComponent build();
    }
}
