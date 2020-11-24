package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.MainModule;
import com.pine.populay_options.mvp.model.di.module.PlayersFragmentModule;
import com.pine.populay_options.mvp.model.di.module.PositionModule;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.PositionActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PlayersFragment;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = PlayersFragmentModule.class, dependencies = AppComponent.class)
public interface PlayersFragmentComponent {
    void inject(PlayersFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PlayersFragmentComponent.Builder view( PlayersFragmentContract.View view);

        PlayersFragmentComponent.Builder appComponent(AppComponent appComponent);

        PlayersFragmentComponent build();
    }
}
