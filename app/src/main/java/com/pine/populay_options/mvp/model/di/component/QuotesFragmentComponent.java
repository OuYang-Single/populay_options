package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.QuotesFragmentModule;
import com.pine.populay_options.mvp.model.di.module.TopicsFragmentModule;
import com.pine.populay_options.mvp.model.mvp.contract.QuotesFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.QuotesFragment;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.TopicsFragment;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = QuotesFragmentModule.class, dependencies = AppComponent.class)
public interface QuotesFragmentComponent {
    void inject(QuotesFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        QuotesFragmentComponent.Builder view( QuotesFragmentContract.View view);

        QuotesFragmentComponent.Builder appComponent(AppComponent appComponent);

        QuotesFragmentComponent build();
    }
}
