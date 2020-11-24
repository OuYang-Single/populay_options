package com.pine.populay_options.mvp.model.di.module;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.PositionOrder;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.model.PaperFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class PaperFragmentModule {
    @Binds
    abstract PaperFragmentContract.Model bindMainModel(PaperFragmentModel model);

    @ActivityScope
    @Provides
    public static List<PositionOrder> getPositionOrderList(){
        return new ArrayList<>();
    }

}
