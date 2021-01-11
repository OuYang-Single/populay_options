package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

public class DetailsPresenter extends BasePresenter<DetailsContract.Model, DetailsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    DetailsAdapter mDetailsAdapter;
    @Inject
    List<CommentsEntity> commentsEntities;
    @Inject
    public DetailsPresenter(DetailsContract.Model model, DetailsContract.View rootView) {
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

    public void initData(Integer id) {
        mModel.comment(id).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<List<CommentsEntity>>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<List<CommentsEntity>> users) {
                        if (users.getStatus()==0){
                            commentsEntities.clear();
                            commentsEntities.addAll(users.getData());
                            mDetailsAdapter.notifyDataSetChanged();
                            if (commentsEntities.size()==0){
                                mRootView.noData();
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });;
    /*    commentsEntities.add(new CommentsEntity());
        commentsEntities.add(new CommentsEntity());
        commentsEntities.add(new CommentsEntity());*/

    }

    public void Unlike(Integer id, Long userId) {
        mModel.Unlike(id,userId).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> users) {
                        if (users.getStatus()==0){
                            if (users.getData()){
                                mRootView.SuccessUnlike();
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
    public void like(Integer id, Long userId) {
        mModel.like(id,userId).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> users) {
                        if (users.getStatus()==0){
                            if (users.getData()){
                                mRootView.SuccessLike();
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
    public void shield(Integer id, Long userId) {
        mModel.shield(id,userId).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> users) {
                        if (users.getStatus()==0){
                            if (users.getData()){
                               mRootView.killMyself();
                                mRootView.showMessage(mApplication.getString(R.string.Blocked));
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }



    public void SubmitComments(long userId, Integer id, String toString) {
        mModel.SubmitComments(id,(int)userId,toString).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示下拉刷新的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                   // mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> users) {
                        if (users.getStatus()==0){
                            if (users.getData()){
                                mRootView.SuccessShield();
                               mRootView.showMessage(mApplication.getString(R.string.Submitted_successfully));
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mRootView.hideLoading();
                    }
                });;
    }

    public void delete(Integer id) {
        mModel.delete(id).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Boolean> users) {
                        if (users.getStatus()==0){
                            if (users.getData()){
                                mRootView.killMyself();
                                mRootView.showMessage(mApplication.getString(R.string.Blocked));
                            }else {
                                mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                            }
                        }else {
                            mRootView.showMessage(mApplication.getString(R.string.Request_failed));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
}