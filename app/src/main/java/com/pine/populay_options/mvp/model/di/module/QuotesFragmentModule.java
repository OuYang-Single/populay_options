package com.pine.populay_options.mvp.model.di.module;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.QuotesFragmentContract;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;
import com.pine.populay_options.mvp.model.mvp.model.QuotesFragmentModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
@Module
public abstract class QuotesFragmentModule {
    @Binds
    abstract QuotesFragmentContract.Model bindMainModel(QuotesFragmentModel model);

    @ActivityScope
    @Provides
    public static List<ExchangEreal> getExchangErealList(){
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static List<String> getStringList(){
        String string1="XAU,AUDCAD,AUDCHF,AUDJPY,AUDNZD,AUDUSD,BTCUSD,CADCHF,CADJPY,CHFJPY";
        String string2="ETHUSD,EURAUD,EURCAD,EURCHF,EURGBP,EURJPY,EURNZD,EURTRY,EURUSD,GBPAUD";
        String string3="GBPCAD,GBPCHF,GBPJPY,GBPNZD,GBPUSD,LTCUSD,NZDCAD,NZDCHF,NZDJPY,NZDUSD";
        String string4="USDCAD,USDCHF,USDCNH,USDHKD,USDJPY,USDSGD";
        List<String> stringList= new ArrayList<>();
        stringList.add(string1);
        stringList.add(string2);
        stringList.add(string3);
        stringList.add(string4);
        return stringList;
    }

}
