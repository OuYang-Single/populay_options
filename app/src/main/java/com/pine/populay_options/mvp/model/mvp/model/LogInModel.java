package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

import javax.inject.Inject;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class LogInModel extends BaseModel implements LogInContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    ManagerFactory mManagerFactory;
    @Inject
    public LogInModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Request<User>> getUsers(String Neme, String Password, boolean b) {
        AuthorizationUser mAuthorizationUser=new AuthorizationUser();
        mAuthorizationUser.setCode("");
        mAuthorizationUser.setUsername(Neme);
        mAuthorizationUser.setPassword(Password);
        mAuthorizationUser.setUuid("");
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getUsers(mAuthorizationUser))
                .flatMap(new Function<Observable<Request<User>>, ObservableSource<Request<User>>>() {
                    @Override
                    public ObservableSource<Request<User>> apply(@NonNull Observable<Request<User>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getLogin(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(b))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public void seve(Request<User> users) {
        mManagerFactory.getStudentManager(mApplication.getApplicationContext()).deleteAll();
        mManagerFactory.getStudentManager(mApplication.getApplicationContext()).save(users.getData());
    }
}