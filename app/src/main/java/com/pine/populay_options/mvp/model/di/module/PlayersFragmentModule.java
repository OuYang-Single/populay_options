package com.pine.populay_options.mvp.model.di.module;

import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.model.PlayersFragmentModel;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PlayersFragmentModule {
    @Binds
    abstract PlayersFragmentContract.Model bindMainModel(PlayersFragmentModel model);
}
