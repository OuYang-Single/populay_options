package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.MineFragmentModule;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.MineFragment;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = MineFragmentModule.class, dependencies = AppComponent.class)
public interface MineFragmentComponent {
    void inject(MineFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MineFragmentComponent.Builder view( MineFragmentContract.View view);

        MineFragmentComponent.Builder appComponent(AppComponent appComponent);

        MineFragmentComponent build();
    }
}