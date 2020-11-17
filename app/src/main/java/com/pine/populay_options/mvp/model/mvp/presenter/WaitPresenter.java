package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.ui.activity.LogInActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

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
    int anInt = 10;

    @Inject
    public WaitPresenter(WaitContract.Model model, WaitContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
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
}
