package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.VideosModule;
import com.pine.populay_options.mvp.model.di.module.WaitModule;
import com.pine.populay_options.mvp.model.mvp.contract.TradersDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.TradersDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.VideosActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = VideosModule.class, dependencies = AppComponent.class)
public interface VideosComponent {
    void inject(VideosActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        VideosComponent.Builder view(VideosContract.View view);

        VideosComponent.Builder appComponent(AppComponent appComponent);

        VideosComponent build();
    }
}
