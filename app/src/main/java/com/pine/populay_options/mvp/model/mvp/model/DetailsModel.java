package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import me.leefeng.promptlibrary.PromptDialog;

@ActivityScope
public class DetailsModel  extends BaseModel implements DetailsContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public DetailsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Request<Boolean>> Unlike(Integer id, Long userId) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .Unlike(id,userId))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .Unlike(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<Boolean>> like(Integer id, Long userId) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .like(id,userId))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .like(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<Boolean>> shield(Integer id, Long userId) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .shield(id,userId))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .shield(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<List<CommentsEntity>>> comment(Integer id) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .comment(id))
                .flatMap(new Function<Observable<Request<List<CommentsEntity>>>, ObservableSource<Request<List<CommentsEntity>>>>() {
                    @Override
                    public ObservableSource<Request<List<CommentsEntity>>> apply(@NonNull Observable<Request<List<CommentsEntity>>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .comment(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<Boolean>> SubmitComments(Integer id, int userId, String toString) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .SubmitComments(userId,id,toString))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .SubmitComments(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<Boolean>> delete(Integer id) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .delete_topics(id))
                .flatMap(new Function<Observable<Request<Boolean>>, ObservableSource<Request<Boolean>>>() {
                    @Override
                    public ObservableSource<Request<Boolean>> apply(@NonNull Observable<Request<Boolean>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .delete_topics(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }
}