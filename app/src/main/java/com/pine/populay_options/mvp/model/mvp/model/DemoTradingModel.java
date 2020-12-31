package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.mvp.model.api.AliyunExchangeApi;
import com.pine.populay_options.mvp.model.entity.AliyunRequest;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.appcode;

@ActivityScope
public class DemoTradingModel extends BaseModel implements DemoTradingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    Retrofit mRetrofit;
    @Inject
    public DemoTradingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
//period	STRING	必选	取 1M,5M,10M,15M,30M,1H,2H,4H,D
//pidx	INT	必选	页码,排序是从当前往历史方向排,第一页是当前处。接口输出的日周期数据只有最近2年，分钟周期数据只有最近5天(更多历史数据可申请打包下载)。
//psize	INT	可选	每页最多500个数据
//symbol	STRING	必选	代码,参考列表
//withlast	INT	可选	是否包含最新的一个动态k线数据，第一页有效


    @Override
    public Observable<AliyunRequest<List<KLineDataModel>>> onChartData( String period,String pidx, String psize,String symbol, String withlast) {

       return   mRetrofit.create(AliyunExchangeApi.class)
                .chart(period,pidx,psize,symbol,withlast, "APPCODE " + appcode)
                ;
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
                .baseUrl(APP_DOMAIN)
                //设置委托,使用OKHttp联网,也可以设置其他的;
                .client(okHttpClients)
                .addConverterFactory(GsonConverterFactory.create())
                //设置支持rxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public Observable<AliyunRequest<ExchangEreal>>  getOffer(String pairs, String withks, String withticks) {
        return   mRetrofit.create(AliyunExchangeApi.class)
                .real(pairs, withks,withticks,"APPCODE " + appcode)
                ;
    }
}
