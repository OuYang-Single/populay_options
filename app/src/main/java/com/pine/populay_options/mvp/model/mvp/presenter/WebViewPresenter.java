package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TradersAdapter;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class WebViewPresenter extends BasePresenter<WebViewContract.Model, WebViewContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;


    @Inject
    public WebViewPresenter(WebViewContract.Model model, WebViewContract.View rootView) {
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


    public void initWebSettings(WebSettings webSettings) {
        String userAgentString = webSettings.getUserAgentString();
        userAgentString = "ANDROID_AGENT_NATIVE/2.0" + " " + userAgentString;
        webSettings.setUserAgentString(userAgentString);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.NORMAL);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(mApplication.getExternalCacheDir().getPath());
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    public void initWebView(WebView webView, AppJs appJs) {
        webView.addJavascriptInterface(appJs, "AppJs");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(false);
        }
        webView.clearHistory();
        webView.setDrawingCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult result = webView.getHitTestResult();
                if (result != null) {
                    int type = result.getType();
                    if (type == WebView.HitTestResult.IMAGE_TYPE) {
                        //TODO实现长按保存图片
                        //showSaveImageDialog(result);
                    }
                }
                return false;
            }
        });

    }

    public void doLogin2(OpenEntity openEntity, GoogleSignInAccount account,int type) {
        mModel.doLogin2(openEntity,account,type).subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<Request<Login>>(mErrorHandler) {
                    @Override
                    public void onNext(Request<Login> data) {
                        if (data.getCode()==200){
                            mRootView.doLogin2(data.getData());
                        }else{
                            mRootView.showMessage(data.getMsg() );
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });;
    }
}
