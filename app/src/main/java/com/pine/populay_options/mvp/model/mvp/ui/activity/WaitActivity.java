package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pine.populay_options.R;

import com.pine.populay_options.app.utils.SPManager;
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
import timber.log.Timber;

import java.io.File;
import java.util.ArrayList;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.mvp.model.mvp.ui.Service.FileUtils.imageToBase64;
import static com.wq.photo.widget.CameraPreview.TAG;
import static com.wq.photo.widget.PickConfig.FILECHOOSER_RESULTCODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;

// setContentView(R.layout.activity_wait);
public class WaitActivity extends BaseActivity<WaitPresenter> implements WaitContract.View, DownloadListener {
    String Tog=WaitActivity.class.getName();
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.wait_time_jump_txt)
    TextView mWaitTimeJumpTxt;
    @BindView(R.id.wait_jump)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.view_live)
    View mView;
    androidx.appcompat.widget.Toolbar mToolbar;
    TextView toolbar_title;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> uploadMessageAboveL;
    String URL = "";
    AppJs appJs;
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

        mPresenter.getToken();
        toolbar_title=(TextView)findViewById(R.id.toolbar_title);
        mToolbar =(androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
        mPresenter.initWebSettings(webView.getSettings());
        appJs=    new AppJs(this,webView);
        mPresenter.initWebView(webView,appJs);
        setTitle( "");
        webView.setDownloadListener(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {

                    if (toolbar_title!=null){
                        toolbar_title.setText(title);
                    }

                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            // Android 3.0 以下
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                mUploadMessage = valueCallback;
                selectImage(WaitActivity.this);
            }

            // Android 3~4.1
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                mUploadMessage = valueCallback;
                selectImage(WaitActivity.this);
            }

            // Android  4.1以上
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                mUploadMessage = valueCallback;
                selectImage(WaitActivity.this);
            }

            // Android 5.0以上
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                selectImage(WaitActivity.this);
                return true;
            }
        });
       // WebSettings webSettings=  webView.getSettings();
        mPresenter.vestSign(appJs);
    }

    public   static void selectImage(Activity context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        context. startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILECHOOSER_RESULTCODE);
    }
    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
       // mPresenter.WaitingTime();

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
    VestSignEntity data;
    @Override
    public void vestSign(VestSignEntity data) {
        data=data;
        if (data.getStatus()==0){
            webView.setVisibility(View.VISIBLE);
            mRelativeLayout.setVisibility(View.GONE);
            webView.loadUrl(data.getH5Url());
            if (data.getBackgroundCol()!=null){
                try {
                    mToolbar.setBackgroundColor(Color.parseColor(data.getBackgroundCol()));
                }catch (Exception e){
                    Timber.i(Tog + " setBackgroundColor=="+e);
                }
            }
            if ("black".equals(data.getFieldCol())){
                toolbar_title.setTextColor(Color.parseColor("#000000"));
            }else {
                toolbar_title.setTextColor(Color.parseColor("#FFFFFF"));
            }

        }else {
            setFullscreen(this);
            webView.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);
            mPresenter.WaitingTime();
        }

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

    @Override
    public void showTitleBar(boolean visible){
        if (mToolbar!=null){
            mToolbar.setVisibility(visible?View.VISIBLE:View.GONE);
            webView.loadUrl(data.getH5Url());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        }else if (requestCode == PICK_REQUEST_CODES && resultCode == Activity.RESULT_OK){
            ArrayList<String> img = data.getStringArrayListExtra("data");
           String callbackMethod= data.getStringExtra("callbackMethod");
            if (!img.isEmpty()){
                 String imgs=    img.get(0);
                if (!TextUtils.isEmpty(imgs)) {
                    StringBuilder builder = new StringBuilder(callbackMethod).append("(");
                    builder.append("'").append("data:image/png;base64,").append(imageToBase64(imgs)).append("'");
                    builder.append(")");
                    String method = builder.toString();
                    String javaScript = "javascript:" + method;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webView.evaluateJavascript(javaScript, null);
                    } else {
                        webView.loadUrl(javaScript);
                    }
                }
            }
        }else if (requestCode == FILECHOOSER_RESULTCODE){

            if (null == mUploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }

        }

    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILECHOOSER_RESULTCODE || uploadMessageAboveL == null){
            uploadMessageAboveL.onReceiveValue(null);
            uploadMessageAboveL=null;
            return;
        }
        Uri[] results = null;
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL=null;

    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * webview没有选择文件也要传null，防止下次无法执行
     */

}
