package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.PageInfo;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

@ActivityScope
public class TopicsFragmentPresenter extends BasePresenter<TopicsFragmentContract.Model, TopicsFragmentContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    TopicsAdapter mTopicsAdapter;

    @Inject
    List<Topics> mTopics;
    int pageSize=10;
    int preEndIndex;
    @Inject
    public TopicsFragmentPresenter(TopicsFragmentContract.Model model, TopicsFragmentContract.View rootView) {
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

    public void initData( int pageNum,long UserId) {
        mModel.initData(pageNum,pageSize,UserId).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                  //  mRootView.showLoading();//显示下拉刷新的进度条
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<Request<PageInfo<Topics>>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<PageInfo<Topics>> users) {
                        if (users.getStatus()==0){

                            if (pageNum==1) {
                                mTopics.clear();//如果是下拉刷新则清空列表
                            }
                            preEndIndex = mTopics.size();//更新之前列表总长度,用于确定加载更多的起始位置
                            mTopics.addAll(users.getData().getList());
                            if (pageNum==1) {
                                mTopicsAdapter.notifyDataSetChanged();
                            } else {
                                mTopicsAdapter.notifyItemRangeInserted(preEndIndex, mTopics.size());
                            }
                             if (mTopics.size()==0){
                                 mRootView.dataNull();
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
      //
    }

    public void Unlike(Integer id, Long userId,int position) {
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
                                mTopics.get(position).setIslike(0);
                                mTopicsAdapter.notifyItemChanged(position,mTopics.get(position));
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
    public void like(Integer id, Long userId,int position) {
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
                                mTopics.get(position).setIslike(1);
                                mTopicsAdapter.notifyItemChanged(position,mTopics.get(position));

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
    public void shield(Integer id, Long userId, int position) {
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
                                mTopics.remove(position);
                                mTopicsAdapter.notifyDataSetChanged();
                                mRootView.status();
                                mRootView.showMessage(mApplication.getString(R.string.Blocked));
                                if (mTopics.size()==0){
                                    mRootView.dataNull();
                                }
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

    public void delete(Integer id, int position) {
        mModel .delete(id).subscribeOn(Schedulers.io())
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
                                mTopics.remove(position);
                                mTopicsAdapter.notifyDataSetChanged();
                                mRootView.status();
                                mRootView.showMessage(mApplication.getString(R.string.Blockeds));
                                if (mTopics.size()==0){
                                    mRootView.dataNull();
                                }
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