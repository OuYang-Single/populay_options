package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.AdEntity;
import com.pine.populay_options.mvp.model.entity.BandItem;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.HomeFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.ForgetPasswordModel;
import com.pine.populay_options.mvp.model.mvp.model.HomeFragmentModel;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;

@Module
public abstract class HomeFragmentModule {
    @Binds
    abstract HomeFragmentContract.Model bindForexContractModel(HomeFragmentModel model);

    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(HomeFragmentContract.View view){
        return new PromptDialog(view.getActivity());
    }


    @ActivityScope
    @Provides
    public static List<BandItem> getBandItemList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static List<ExchangEreal> getExchangErealList() {
        return new ArrayList<>();
    }



    @ActivityScope
    @Provides
    public static List<AdEntity> getAdEntityList() {
        return new ArrayList<>();
    }


    @ActivityScope
    @Provides
    public static HomeItemAdPagerAdapter getHomeItmeAdPagerAdapter(List<AdEntity> adEntityList, HomeFragmentContract.View view) {
        return new HomeItemAdPagerAdapter(adEntityList,view.getActivity(),view);
    }
}