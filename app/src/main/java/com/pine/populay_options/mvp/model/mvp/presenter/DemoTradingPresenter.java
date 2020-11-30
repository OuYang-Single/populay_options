package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.mvp.model.entity.AliyunRequest;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;


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
  int anInt=0;
    private static final int PERIOD =  1000;
    private static final int DELAY = 100;
    private Disposable mDisposable;
    public int  pidx=1;
    public int psize=500;
    @Inject
    public DemoTradingPresenter(DemoTradingContract.Model model, DemoTradingContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();

    }


    public void timeLoop(String pairs) {
        mDisposable = Observable.interval(DELAY, PERIOD, TimeUnit.MILLISECONDS)
                .map((aLong -> aLong + 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong ->{
 getOffer(pairs,"1","1");
                        });

       //getUnreadCount()执行的任务
    }

    public void getOffer(String pairs,String withks, String withticks){
                 mModel.getOffer(pairs,withks,withticks).subscribeOn(Schedulers.io())
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
                         .subscribe(new ErrorHandleSubscriber<AliyunRequest<ExchangEreal>>(mErrorHandler) {
                             @Override
                             public void onNext(AliyunRequest<ExchangEreal> mExchangeChart) {
                                 Timber.w(mExchangeChart.getObj().toString());
                                 mRootView.getOffer(mExchangeChart.getObj());

                                 // mRootView.showMessage("Name" +users.getUser().getUsername());
                             }

                             @Override
                             public void onError(Throwable t) {
                                 super.onError(t);

                             }
                         });;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        if (mDisposable != null) mDisposable.dispose();
    }

    public void onChartData(String period,String symbol, String withlast,int type) {

        mModel.onChartData(period,pidx+"",psize+"",symbol,withlast).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<AliyunRequest<List<KLineDataModel>>>(mErrorHandler) {
                    @Override
                    public void onNext(AliyunRequest<List<KLineDataModel>> mExchangeChart) {
                        Collections.reverse(mExchangeChart.getObj());
                        mRootView.onCharData( mExchangeChart.getObj(),symbol,  period,type);
                        pidx++;
                        // mRootView.showMessage("Name" +users.getUser().getUsername());
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
}
