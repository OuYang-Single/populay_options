package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

@ActivityScope
public class ForgetPasswordModel extends BaseModel implements  ForgetPasswordContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;


    @Inject
    public ForgetPasswordModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
    @Override
    public Observable<Request<Boolean>> isUserExists(String name, String defaultRegion) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .isUserExists(name,defaultRegion))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .isUserExists(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }


    @Override
    public Observable<Request<Boolean>> changePassword(String phone, String password) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .changePassword(phone,password))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .changePassword(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }
}