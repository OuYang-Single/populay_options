package com.pine.populay_options.mvp.model.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.di.module.ForexCalendarModule;
import com.pine.populay_options.mvp.model.di.module.ForexModule;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForexActivity;
import com.pine.populay_options.mvp.model.mvp.ui.fragment.ForexCalendarFragment;

import dagger.BindsInstance;
import dagger.Component;
@ActivityScope
@Component(modules = ForexCalendarModule.class, dependencies = AppComponent.class)
public interface ForexCalendarComponent {
    void inject(ForexCalendarFragment activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ForexCalendarComponent.Builder view(ForexCalendarContract.View view);

        ForexCalendarComponent.Builder appComponent(AppComponent appComponent);

        ForexCalendarComponent build();
    }
}
