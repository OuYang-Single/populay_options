package com.pine.populay_options.mvp.model.di.module;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.TradersEntity;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.model.AddDetailsModel;
import com.pine.populay_options.mvp.model.mvp.model.TradersModel;

import java.util.ArrayList;
import java.util.List;

import cz.kinst.jakub.view.StatefulLayout;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NODATA;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static cz.kinst.jakub.view.SimpleStatefulLayout.State.PROGRESS;

@Module
public   abstract class TradersModule {
    @Binds
    abstract TradersContract.Model bindTradersContractModel(TradersModel model);

    @ActivityScope
    @Provides
    public static List<TradersEntity> getTradersList(){
        return new ArrayList<>();
    }


}
