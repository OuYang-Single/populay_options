package com.pine.populay_options.mvp.model.di.module;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;
import com.pine.populay_options.mvp.model.mvp.model.WaitModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class PositionModule {
    @Binds
    abstract PositionContract.Model bindMainModel(PositionModel model);

    @ActivityScope
    @Provides
    public static List<Fragment> getFragmentList(){
        return new ArrayList<>();
    }
}
