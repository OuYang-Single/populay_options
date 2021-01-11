package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.LoginEvent;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.BookDetalisContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.Model,ForgetPasswordContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ForgetPasswordPresenter(ForgetPasswordContract.Model model, ForgetPasswordContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void isUserExists(String phone, String defaultRegion) {
        mModel.isUserExists(phone,defaultRegion).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> isUserExists) {
                        if (isUserExists.getStatus()==0){
                            if (isUserExists.getData()){
                                mRootView.getCode();
                                SMSSDK.getVerificationCode(defaultRegion, phone, null, new OnSendMessageHandler() {
                                    @Override
                                    public boolean onSendMessage(String s, String s1) {
                                        return false;
                                    }
                                });
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.account_does_not_exist));
                            }
                        }else {
                            mRootView.showMessage(isUserExists.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }

    public void changePassword(String phone,String defaultRegion,String code,String password) {
        SMSSDK. submitVerificationCode(defaultRegion,phone,code);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者(Observable) & 定义需发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                while (true){
                    if( mRootView.getResult()!=-2){
                        emitter.onNext(mRootView.getResult());
                        break;
                    }
                }
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 创建观察者(Observer) & 定义响应事件的行为
            // 3. 通过订阅（subscribe）连接观察者和被观察者
            @Override
            public void onSubscribe(Disposable d) {

            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                if (value==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                    mModel.changePassword(phone,password).subscribeOn(Schedulers.io())
                            .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(disposable -> {
                                mRootView.showLoading();
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .doFinally(() -> {
                                mRootView.hideLoading();
                            })
                            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                            .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                                @Override
                                public void onNext(Request<Boolean> users) {
                                    if (users.getStatus()==0){
                                      if (users.getData()){
                                          mRootView.showMessage(mApplication.getString(R.string.resend_new_passwored));
                                          mRootView.killMyself();
                                      }else {
                                          mRootView.showMessage(mApplication.getString(R.string.resend_new_passwored_e));
                                      }
                                    }else {
                                        mRootView.showMessage(users.getMsg());
                                    }
                                }

                                @Override
                                public void onError(Throwable t) {
                                    super.onError(t);

                                }
                            });;
                }else {
                    mRootView.showMessage(mApplication.getString(R.string.verification_code_error));
                }
                mRootView.initResult();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        });
    }
}