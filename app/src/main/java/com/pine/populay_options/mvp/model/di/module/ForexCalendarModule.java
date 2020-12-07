package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.entity.ForexCalendar;
import com.pine.populay_options.mvp.model.mvp.contract.ForexCalendarContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.model.ForexCalendarModel;
import com.pine.populay_options.mvp.model.mvp.model.ForexModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract  class ForexCalendarModule {
    @Binds
    abstract ForexCalendarContract.Model bindForexCalendarContractModel(ForexCalendarModel model);

    @ActivityScope
    @Provides
    public static List<ForexCalendar> getPositionForexCalendarList(){
        return new ArrayList<>();
    }

}
