package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Billboard;
import com.pine.populay_options.mvp.model.entity.PositionOrder;
import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.model.PlayersFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class PlayersFragmentModule {
    @Binds
    abstract PlayersFragmentContract.Model bindMainModel(PlayersFragmentModel model);
    @ActivityScope
    @Provides
    public static List<Billboard> getBillboardList(){
        return new ArrayList<>();
    }

}
