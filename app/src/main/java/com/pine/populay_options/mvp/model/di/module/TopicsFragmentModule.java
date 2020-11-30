package com.pine.populay_options.mvp.model.di.module;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;
import com.pine.populay_options.mvp.model.mvp.model.TopicsFragmentModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TopicsFragmentModule {
    @Binds
    abstract TopicsFragmentContract.Model bindTopicsFragmentModel(TopicsFragmentModel model);
    @ActivityScope
    @Provides
    public static List<Topics> getTopicsList(){
        return new ArrayList<>();
    }

}
