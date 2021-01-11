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
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
public class AddDetailsModel extends BaseModel implements AddDetailsContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    ManagerFactory mManagerFactory;
    @Inject
    public AddDetailsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Request<String>> add(String content,List<String>paths) {
        int i=1;
        Map<String, RequestBody> map = new HashMap<>();
        for (String path:paths){
            File file = new File(path);
            map.put("file"+i+"\""+"; filename=\""+file.getName(),RequestBody.create(MediaType.parse("image/png"),file));
            i++;
        }
        if (map.size()<=0){
            File file = new File("");
            map.put("file"+i+"\""+"; filename=\"",RequestBody.create(MediaType.parse("image/png"),file));
        }
     List<User>users= mManagerFactory.getStudentManager(mApplication).queryAll();
       String id= users.get(0).getId()+"";
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .addDetails(Integer.parseInt(id),content,map))
                .flatMap(new Function<Observable<Request<String>>, ObservableSource<Request<String>>>() {
                    @Override
                    public ObservableSource<Request<String>> apply(@NonNull Observable<Request<String>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .addDetails(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public Observable<Request<String>> adds(String content) {

        List<User>users= mManagerFactory.getStudentManager(mApplication).queryAll();
        String id= users.get(0).getId()+"";
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .addDetailss(Integer.parseInt(id),content))
                .flatMap(new Function<Observable<Request<String>>, ObservableSource<Request<String>>>() {
                    @Override
                    public ObservableSource<Request<String>> apply(@NonNull Observable<Request<String>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .addDetails(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }
}