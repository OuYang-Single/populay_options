package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.entity.PositionOrder;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.PaperAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class MineFragmentPresenter  extends BasePresenter<MineFragmentContract.Model, MineFragmentContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    ManagerFactory mManagerFactory;
    @Inject
    public MineFragmentPresenter(MineFragmentContract.Model model, MineFragmentContract.View rootView) {
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

    public void initData() {

    }

    public void logOut() {
        mModel.logOut().subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<String>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<String> users) {
                        if(users.getStatus()==0){
                            mRootView.LogOut();
                        }else {
                            mRootView.showMessage(users.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }

    public void modifyAvatar(String img, Long userId) {
        mModel.modifyAvatar(img,userId).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<User>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<User> users) {
                        if(users.getStatus()==0){
                            mManagerFactory.getStudentManager(mApplication).deleteAll();
                            mManagerFactory.getStudentManager(mApplication).saveOrUpdate(users.getData());
                            mRootView.modifyAvatar(users.getData());
                            mRootView.showMessage(mApplication.getString(R.string.Submission_Faileds));
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Submission_Failed));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }
}
