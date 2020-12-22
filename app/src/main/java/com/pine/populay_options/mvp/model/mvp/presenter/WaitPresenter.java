package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import timber.log.Timber;

import javax.inject.Inject;

import static com.pine.populay_options.mvp.model.di.module.WaitModule.TIMEJUMPTXT;

@ActivityScope
public class WaitPresenter extends BasePresenter<WaitContract.Model, WaitContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    Handler mHandler;
    Runnable mRunnable;
    int Time = 1000;
    int anInt = 5;

    @Inject
    public WaitPresenter(WaitContract.Model model, WaitContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        mModel.onStart();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                anInt--;
                Log.w("mRunnable", anInt + "");
                if (anInt != 0) {
                    mHandler.postDelayed(mRunnable, Time);
                }else{
                    Jump();
                }
                Message message = new Message();
                message.what = TIMEJUMPTXT;
                message.arg1 = anInt;
                mHandler.sendMessage(message);
            }
        };

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void WaitingTime() {
        mRootView.setTimeJumpTxt(mRootView.getContent().getString(R.string.wait_jump_over)+"("+anInt + ")");
        mRootView.setEventSwitch(true);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, Time);

    }

    public void Jump() {
        Intent intent = null;
        if (mModel.isUserPresence()) {
            intent = new Intent(mRootView  .getContent(), MainActivity.class);
        }else {
            intent = new Intent(mRootView.getContent(), LogInActivity.class);
        }
        mRootView.launchActivity(intent);
    }

    public void vestSign(AppJs appJs) {
        mModel.vestSign(appJs).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<VestSignEntity>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<VestSignEntity> data) {
                       if (data.getCode()==200){
                           mRootView.vestSign(data.getData());
                       }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
}
