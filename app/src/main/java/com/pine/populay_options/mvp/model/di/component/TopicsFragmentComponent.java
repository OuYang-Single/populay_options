package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.PositionModule;
import com.pine.populay_options.mvp.model.di.module.TopicsFragmentModule;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.PositionActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.TopicsFragment;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = TopicsFragmentModule.class, dependencies = AppComponent.class)
public interface TopicsFragmentComponent {
    void inject(TopicsFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TopicsFragmentComponent.Builder view( TopicsFragmentContract.View view);

        TopicsFragmentComponent.Builder appComponent(AppComponent appComponent);

        TopicsFragmentComponent build();
    }
}
