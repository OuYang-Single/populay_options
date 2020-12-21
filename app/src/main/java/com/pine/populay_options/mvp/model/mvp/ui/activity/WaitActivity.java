package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import com.pine.populay_options.R;

import com.pine.populay_options.mvp.model.di.component.DaggerWaitComponent;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.presenter.WaitPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.CustomizeView.FullScreenVideoView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import java.io.File;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.jess.arms.utils.Preconditions.checkNotNull;

// setContentView(R.layout.activity_wait);
public class WaitActivity extends BaseActivity<WaitPresenter> implements WaitContract.View {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.wait_time_jump_txt)
    TextView mWaitTimeJumpTxt;
    String URL = "";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWaitComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setFullscreen(this);
        return R.layout.activity_wait;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).request();
        WebSettings webSettings=  webView.getSettings();
        String userAgentString = webSettings.getUserAgentString();
        userAgentString = "ANDROID_AGENT_NATIVE/2.0" + " " + userAgentString;
        webSettings.setUserAgentString(userAgentString);
      webView.addJavascriptInterface(new AppJs(this,webView), "AppJs");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.NORMAL);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getExternalCacheDir().getPath());
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(false);
        }
//TODO 需要实现WebViewClient和WebChromeClient
// onShowFileChooser() 选择文件(图片)
      //  webView.setWebChromeClient();
// onPageFinished在里面可以获取webView.getTitle()给titlebar设置标题
       // webView.setWebViewClient();

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
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimeType, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        mPresenter.vestSign();
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        mPresenter.WaitingTime();

    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
    }

    @OnClick({R.id.wait_time_jump_txt})
    public void OnClick(View mView) {
        switch (mView.getId()) {
            case R.id.wait_time_jump_txt:
                mPresenter.Jump();
                break;
        }
    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public void setEventSwitch(boolean isSwitch) {
        //     mWaitTimeJumpTxt.setClickable(isSwitch);
    }

    @Override
    public void setTimeJumpTxt(@NonNull String isSwitch) {
        try {
            if (mWaitTimeJumpTxt!=null&&isSwitch!=null){
                mWaitTimeJumpTxt.setText(isSwitch);
            }
        }catch (Exception e){

        }

    }

    @Override
    public void statusService(Intent intent) {

    }

    @Override
    public void vestSign(VestSignEntity data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@io.reactivex.annotations.NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
        killMyself();
    }

    @Override
    public void killMyself() {
        finish();
    }
}
