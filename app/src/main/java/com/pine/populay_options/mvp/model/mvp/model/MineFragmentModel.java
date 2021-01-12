package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PlayersFragmentContract;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@ActivityScope
public class MineFragmentModel extends BaseModel implements MineFragmentContract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MineFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Request<String>> logOut() {

        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getLogout())
                .flatMap(new Function<Observable<Request<String>>, ObservableSource<Request<String>>>() {
                    @Override
                    public ObservableSource<Request<String>> apply(@NonNull Observable<Request<String>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .logOut(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<User>> modifyAvatar(String img, long userId) {
        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(img);
        map.put("file1"+"\""+"; filename=\""+file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .modifyAvatar((int)userId,map))
                .flatMap(new Function<Observable<Request<User>>, ObservableSource<Request<User>>>() {
                    @Override
                    public ObservableSource<Request<User>> apply(@NonNull Observable<Request<User>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .modifyAvatar(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }
}