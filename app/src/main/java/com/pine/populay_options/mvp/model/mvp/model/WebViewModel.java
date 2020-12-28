package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.VideosContract;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import timber.log.Timber;

@ActivityScope
public class WebViewModel extends BaseModel implements WebViewContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WebViewModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
/*new Function<Observable<Request<Login>>, ObservableSource<Request<Login>>>() {
        @Override
        public ObservableSource<Request<Login>> apply(@NonNull Observable<Request<Login>> listObservable) throws Exception {
            return mRepositoryManager.obtainCacheService(CommonCache.class)
                    .doLogin2(listObservable
                            , new DynamicKey(1)
                            , new EvictDynamicKey(true))
                    .map(Reply::getData);
        }
    }*/
    @Override
    public Observable<Request<Login>> doLogin2(OpenEntity openEntity, GoogleSignInAccount account,int type) {
        Timber.w( "doLogin2   "+" openGoogle"+"   openEntity.getHost()=="+ openEntity.getHost()+"   account.getId()==="+account.getId()+"  account.getIdToken()==="+account.getDisplayName()+"  account.getEmail()==="+account.getEmail()+"   account.getSign()==="+openEntity.getSign());
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .doLogin2( openEntity.getHost(),account.getId(),account.getDisplayName()==null?"":account.getDisplayName(),account.getEmail(),type,openEntity.getSign())).flatMap(new Function<Observable<Request<Login>>, ObservableSource<Request<Login>>>() {
            @Override
            public ObservableSource<Request<Login>> apply(@NonNull Observable<Request<Login>> listObservable) throws Exception {
                return mRepositoryManager.obtainCacheService(CommonCache.class)
                        .doLogin2(listObservable
                                , new DynamicKey(1)
                                , new EvictDynamicKey(true))
                        .map(Reply::getData);
            }
        })
               ;
    }
}