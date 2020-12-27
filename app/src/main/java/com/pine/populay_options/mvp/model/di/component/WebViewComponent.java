package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.WaitModule;
import com.pine.populay_options.mvp.model.di.module.WebViewModule;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WaitActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WebViewActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = WebViewModule.class, dependencies = AppComponent.class)
public interface WebViewComponent {
    void inject(WebViewActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WebViewComponent.Builder view(WebViewContract.View view);

        WebViewComponent.Builder appComponent(AppComponent appComponent);

        WebViewComponent build();
    }
}