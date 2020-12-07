package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.VideoModule;
import com.pine.populay_options.mvp.model.di.module.VideosModule;
import com.pine.populay_options.mvp.model.mvp.contract.VideoContract;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.VideoActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.VideosActivity;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = VideoModule.class, dependencies = AppComponent.class)
public interface VideoComponent {
    void inject(VideoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        VideoComponent.Builder view(VideoContract.View view);

        VideoComponent.Builder appComponent(AppComponent appComponent);

        VideoComponent build();
    }
}
