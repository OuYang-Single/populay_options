package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

@ActivityScope
public class TopicsFragmentModel  extends BaseModel implements TopicsFragmentContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public TopicsFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Request<List<Topics>>> initData(int pageNum,int pageSize) {

        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .initData(pageNum,pageSize))
                .flatMap(new Function<Observable<Request<List<Topics>>>, ObservableSource<Request<List<Topics>>>>() {
                    @Override
                    public ObservableSource<Request<List<Topics>>> apply(@NonNull Observable<Request<List<Topics>>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .initData(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }
}