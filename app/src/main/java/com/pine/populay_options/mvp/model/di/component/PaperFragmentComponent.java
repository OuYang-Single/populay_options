package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.PaperFragmentModule;
import com.pine.populay_options.mvp.model.di.module.PositionModule;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.PositionActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.PaperFragment;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = PaperFragmentModule.class, dependencies = AppComponent.class)
public interface PaperFragmentComponent {
    void inject(PaperFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        PaperFragmentComponent.Builder view( PaperFragmentContract.View view);

        PaperFragmentComponent.Builder appComponent(AppComponent appComponent);

        PaperFragmentComponent build();
    }
}
