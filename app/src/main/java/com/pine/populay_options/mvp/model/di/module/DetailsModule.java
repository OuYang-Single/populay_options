package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.PositionOrder;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.DetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.TopicsFragmentModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DetailsModule {
    @Binds
    abstract DetailsContract.Model bindTopicsFragmentModel(DetailsModel model);
    @ActivityScope
    @Provides
    public static List<CommentsEntity> getPositionOrderList(){
        return new ArrayList<>();
    }

}
