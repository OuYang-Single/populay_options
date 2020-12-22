package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;
import android.provider.ContactsContract;

import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.api.AliyunExchangeApi;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.api.cache.CommonCache;
import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.appcode;
import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAINS;


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
public class WaitModel extends BaseModel implements WaitContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    ManagerFactory mManagerFactory;
    Retrofit mRetrofit;
    @Inject
    public WaitModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public boolean isUserPresence() {

        return  mManagerFactory.getStudentManager( mApplication.getApplicationContext()).queryAll().size()>0;
    }

    @Override
    public IRepositoryManager getRepositoryManager() {
        return mRepositoryManager;
    }

    @Override
    public Observable<Request<VestSignEntity>> vestSign(AppJs appJs) {
        Calendar  calendars = Calendar.getInstance();

       return Observable.just(mRepositoryManager
                .obtainRetrofitService(Api.class)
                .vestSign( Api.VEST_CODE,appJs.takeChannel(),"1.0.0",appJs.getDeviceId(),calendars.getTime().getTime()))
                .flatMap(new Function<Observable<Request<VestSignEntity>>, ObservableSource<Request<VestSignEntity>>>() {
                    @Override
                    public ObservableSource<Request<VestSignEntity>> apply(@NonNull Observable<Request<VestSignEntity>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .vestSign(listObservable
                                        , new DynamicKey(1)
                                        , new EvictDynamicKey(true))
                                .map(Reply::getData);
                    }
                });
    }

    @Override
    public void onStart() {
        OkHttpClient okHttpClients= new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //允许失败重试
                .retryOnConnectionFailure(true)
                .build();
        mRetrofit=    new Retrofit.Builder()
                //设置基站地址(基站地址+描述网络请求的接口上面注释的Post地址,就是要上传文件到服务器的地址,
                // 这只是一种设置地址的方法,还有其他方式,不在赘述)
                .baseUrl(APP_DOMAINS)
                //设置委托,使用OKHttp联网,也可以设置其他的;
                .client(okHttpClients)
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持rxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}