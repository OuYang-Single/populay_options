package com.pine.populay_options.mvp.model.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.model.CustomerModel;
import com.pine.populay_options.mvp.model.mvp.model.ForexModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


@Module
public abstract class ForexModule {
    @Binds
    abstract ForexContract.Model bindForexContractModel(ForexModel model);


    @ActivityScope
    @Provides
    public static List<Book> getPositionBookList(){
        return new ArrayList<>();
    }

}