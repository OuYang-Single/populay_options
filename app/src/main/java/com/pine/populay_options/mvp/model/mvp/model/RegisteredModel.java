package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.RegisteredContract;
import com.pine.populay_options.mvp.model.mvp.contract.StudyForexContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

@ActivityScope
public class RegisteredModel  extends BaseModel implements RegisteredContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public RegisteredModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    @Override
    public Observable<Request<String>> getRegistered(String Neme, String Password, boolean b) {
        User mAuthorizationUser=new User();
        mAuthorizationUser.setUsername(Neme);
        mAuthorizationUser.setPassword(Password);
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getRegistered(Neme,Password))
                .flatMap(new Function<Observable<Request<String>>, ObservableSource<Request<String>>>() {
                    @Override
                    public ObservableSource<Request<String>> apply(@NonNull Observable<Request<String>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getRegistered(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(b))
                                .map(Reply::getData);
                    }
                });
    }
}