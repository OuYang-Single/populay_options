package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.mvp.model.entity.AliyunRequest;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.entity.HomeEvent;
import com.pine.populay_options.mvp.model.entity.HomeEvents;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.QuotesFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.QuotesAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class QuotesFragmentPresenter extends BasePresenter<QuotesFragmentContract.Model, QuotesFragmentContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    QuotesAdapter mQuotesAdapter;
    @Inject
    List<ExchangEreal> mTopics;
    List<ExchangEreal> mTopic;
    List<ExchangEreal> getmTopics;
    List<ExchangEreal> getmTopicd;
    @Inject
    List<String> stringList;
    private static final int PERIOD = 1500;
    private static final int DELAY = 3000;
    private Disposable mDisposable;
    int anInt = 0;

    @Inject
    public QuotesFragmentPresenter(QuotesFragmentContract.Model model, QuotesFragmentContract.View rootView) {
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
        if (mDisposable != null) {
            mDisposable.dispose();
        }

    }


    public void initData() {
        getQuotes();
    }


    private void getQuotes() {
        getOffers(0);
        mTopic = new ArrayList<>();
        mDisposable = Observable.interval(DELAY, PERIOD, TimeUnit.MILLISECONDS)
                .map((aLong -> aLong + 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                  getOffer(0);
                });
    }

    private void getOffer(int i) {
        if (i == 0) {
            mTopic.clear();
        }
        if (i == 4) {
            for (int i1 = 0; i1 < mTopic.size(); i1++) {
                if (i1 >= mTopics.size()) {
                    break;
                }
                if (mTopics.get(i1).getP() - mTopic.get(i1).getP() > 0) {
                    mTopic.get(i1).setPAndn(1);
                } else if (mTopics.get(i1).getP() - mTopic.get(i1).getP() < 0) {
                    mTopic.get(i1).setPAndn(-1);
                } else {
                    mTopic.get(i1).setPAndn(0);
                }

                mTopics.set(i1, mTopic.get(i1));
            }
            mRootView.notifyDataSetChanged(mTopics);
            getmTopicd = new ArrayList<>();
            for (ExchangEreal exchangEreal : mTopics) {
                getmTopicd.add(exchangEreal);
            }
            Collections.sort(getmTopicd, new Comparator<ExchangEreal>() {
                @Override
                public int compare(ExchangEreal u1, ExchangEreal u2) {
                    double diff = u1.getZF() - u2.getZF();
                    if (diff > 0) {
                        return -1;
                    } else if (diff < 0) {
                        return 1;
                    }
                    return 0; //相等为0
                }
            }); // 按年龄排序

            EventBus.getDefault().post(new HomeEvents( getmTopicd.subList(0,20)));
            return;
        }
        Timber.i("getOffers   ---" + i + "");
        mModel.getOffers(stringList.get(i)).
                subscribeOn(Schedulers.io())
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<AliyunRequest<List<ExchangEreal>>>(mErrorHandler) {
                    @Override
                    public void onNext(AliyunRequest<List<ExchangEreal>> mExchangeChart) {
                        Timber.w(mExchangeChart.getObj().size() + "");
                        if (mExchangeChart.getObj() != null && mExchangeChart.getObj().size() > 0) {
                            mTopic.addAll(mExchangeChart.getObj());
                            getOffer(i + 1);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
        ;
    }

    ;

    private void getOffers(int i) {
        Timber.i("getOffers   ---" + i + "");
        if (i == 4) {
            mRootView.notifyDataSetChanged(mTopics);
            getmTopics = new ArrayList<>();
            getmTopics = new ArrayList<>();
            for (ExchangEreal exchangEreal : mTopics) {
                if ("XAU".equals(exchangEreal.getFS())) {
                    getmTopics.add(exchangEreal);
                }
                if ("EURUSD".equals(exchangEreal.getFS())) {
                    getmTopics.add(exchangEreal);
                }
                if ("USDJPY".equals(exchangEreal.getFS())) {
                    getmTopics.add(exchangEreal);
                }

            }
            EventBus.getDefault().post(new HomeEvent(getmTopics));
            getmTopicd = new ArrayList<>();
            for (ExchangEreal exchangEreal : mTopics) {
                getmTopicd.add(exchangEreal);
            }
            Collections.sort(getmTopicd, new Comparator<ExchangEreal>() {
                @Override
                public int compare(ExchangEreal u1, ExchangEreal u2) {
                    double diff = u1.getZF() - u2.getZF();
                    if (diff > 0) {
                        return -1;
                    } else if (diff < 0) {
                        return 1;
                    }
                    return 0; //相等为0
                }
            }); // 按年龄排序

            EventBus.getDefault().post(new HomeEvents( getmTopicd.subList(0,20)));
            return;
        }
        mModel.getOffers(stringList.get(i)).
                subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable ->
                {
                    mRootView.showLoading();//显示下拉刷新的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() ->
                {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<AliyunRequest<List<ExchangEreal>>>(mErrorHandler) {
                            @Override
                            public void onNext(AliyunRequest<List<ExchangEreal>> mExchangeChart) {
                                Timber.w(mExchangeChart.getObj().size() + "");
                                if (mExchangeChart.getObj() != null && mExchangeChart.getObj().size() > 0) {
                                    mTopics.addAll(mExchangeChart.getObj());
                                    getOffers(i + 1);
                                } else {

                                }
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);

                            }
                        });
        ;
    }

    ;
}
