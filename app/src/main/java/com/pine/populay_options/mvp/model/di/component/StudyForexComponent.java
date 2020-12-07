package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.StudyForexModule;
import com.pine.populay_options.mvp.model.di.module.VideoModule;
import com.pine.populay_options.mvp.model.mvp.contract.StudyForexContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.StudyForexActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.VideoActivity;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = StudyForexModule.class, dependencies = AppComponent.class)
public interface StudyForexComponent {
    void inject(StudyForexActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        StudyForexComponent.Builder view(StudyForexContract.View view);

        StudyForexComponent.Builder appComponent(AppComponent appComponent);

        StudyForexComponent build();
    }
}
