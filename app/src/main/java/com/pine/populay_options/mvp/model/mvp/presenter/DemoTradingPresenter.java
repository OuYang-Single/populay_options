package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.mvp.model.entity.ExchangeChart;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.util.EmptyComponent;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.pine.populay_options.mvp.model.api.AliyunExchangeApi.APP_DOMAIN;

@ActivityScope
public class DemoTradingPresenter extends BasePresenter<DemoTradingContract.Model, DemoTradingContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public DemoTradingPresenter(DemoTradingContract.Model model, DemoTradingContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void onChartData(String count ,String pairs,  String urlNam) {

        mModel.onChartData(count,pairs,urlNam).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<List<KLineDataModel>>(mErrorHandler) {
                    @Override
                    public void onNext(List<KLineDataModel> mExchangeChart) {
                      Log.wtf("ExchangeChart","ExchangeChart");
                      mRootView.onCharData(mExchangeChart,pairs ,  urlNam);
                        // mRootView.showMessage("Name" +users.getUser().getUsername());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
}
