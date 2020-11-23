package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.MainModule;
import com.pine.populay_options.mvp.model.di.module.PositionModule;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.PositionActivity;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = PositionModule.class, dependencies = AppComponent.class)
public interface PositionComponent {
    void inject(PositionActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PositionComponent.Builder view( PositionContract.View view);

        PositionComponent.Builder appComponent(AppComponent appComponent);

        PositionComponent build();
    }
}
