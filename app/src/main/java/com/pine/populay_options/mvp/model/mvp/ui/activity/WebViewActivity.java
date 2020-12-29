package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerWebViewComponent;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.PaytmEntity;
import com.pine.populay_options.mvp.model.entity.PureBrowserEntity;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;
import com.pine.populay_options.mvp.model.mvp.presenter.WebViewPresenter;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import am.widget.stateframelayout.StateFrameLayout;
import butterknife.BindView;

import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.pine.populay_options.mvp.model.mvp.ui.Service.FileUtils.imageToBase64;
import static com.pine.populay_options.mvp.model.mvp.ui.activity.WaitActivity.selectImage;
import static com.wq.photo.widget.PickConfig.ActivityRequestCode;
import static com.wq.photo.widget.PickConfig.FILECHOOSER_RESULTCODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;

public class WebViewActivity extends BaseActivity<WebViewPresenter> implements WebViewContract.View, DownloadListener, View.OnClickListener {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.view_live)
    View view_live;

    androidx.appcompat.widget.Toolbar mToolbar;
    TextView toolbar_title;
    AppJs appJs;
    int type;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> uploadMessageAboveL;
    OpenEntity openEntity;
    PaytmEntity paytmEntity;
    int   returns;
    String BackPressJSMethod;
    PureBrowserEntity mPureBrowserEntity;
    @BindView(R.id.sfl_lyt_state)
    StateFrameLayout lytState;
    View activity_error;
    ImageView activity_error_img;
    TextView activity_error_text;
    Button tv_btn;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWebViewComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web_view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.GONE);
        lytState.loading();
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            String url = getIntent().getStringExtra("URL");
            if (url != null) {
                webView.loadUrl(url);
            }
        } else if (type==2){
            String url = getIntent().getStringExtra("URL");
            paytmEntity=  getIntent().getParcelableExtra("data");
            if (url != null&&paytmEntity!=null) {
                StringBuilder postData = new StringBuilder();
                String postUrl =
                        url + "?mid=" + paytmEntity.getMid() + "&orderId=" + paytmEntity.getOrderId();
                postData.append("MID=").append(paytmEntity.getMid())
                        .append("&txnToken=").append(paytmEntity.getTextToken())
                        .append("&AMOUNT=").append(paytmEntity.getAmount())
                        .append("&ORDER_ID=").append(paytmEntity.getOrderId());
                webView.postUrl(postUrl, postData.toString().getBytes());
            }
        }else {
            mPureBrowserEntity=   getIntent().getParcelableExtra("data");
           if (mPureBrowserEntity!=null){
               setTitle(mPureBrowserEntity.getTitle());
               mToolbar.setVisibility(mPureBrowserEntity.isHasTitleBar()?View.VISIBLE:View.GONE);
               webView.loadUrl(mPureBrowserEntity.getUrl());
               view_live.setVisibility(mPureBrowserEntity.isHasTitleBar()?View.VISIBLE:View.GONE);
               if ("black".equals(mPureBrowserEntity.getStateBarTextColor())){
                   setStatusBarMode(this, true , Color.parseColor(mPureBrowserEntity.getTitleColor()));
                   // toolbar_title.setTextColor(Color.parseColor("#000000"));
               }else {
                   setStatusBarMode(this, false , Color.parseColor(mPureBrowserEntity.getTitleColor()));
               }
               toolbar_title.setTextColor(  Color.parseColor(mPureBrowserEntity.getTitleTextColor()));
               toolbar_title.setText( mPureBrowserEntity.getTitle());
           }

        }
        activity_error=  getLayoutInflater().inflate(R.layout.activity_error, null, false);
        activity_error_img=   activity_error.findViewById(R.id.image);
        activity_error_text=   activity_error.findViewById(R.id.tv_content);
        tv_btn=   activity_error.findViewById(R.id.tv_btn);
        tv_btn.setOnClickListener(this);
        lytState.setStateViews(getLayoutInflater().inflate(R.layout.custom_app_loading, null, false),activity_error,getLayoutInflater().inflate(R.layout.custom_network_error, null, false));


        mPresenter.initWebSettings(webView.getSettings());
        appJs = new AppJs(this, webView);
        mPresenter.initWebView(webView, appJs);
        webView.setDownloadListener(this);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    if (toolbar_title != null) {
                        if (type==0){
                            if (mPureBrowserEntity!=null){
                                if (mPureBrowserEntity.isHasTitleBar()) {
                                    toolbar_title.setText(title);
                                }
                            }
                        }else {
                            toolbar_title.setText(title);
                        }
                    }
                }
                lytState.normal();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址

                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            // Android 3.0 以下
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                mUploadMessage = valueCallback;
                selectImage(WebViewActivity.this);
            }

            // Android 3~4.1
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                mUploadMessage = valueCallback;
                selectImage(WebViewActivity.this);
            }

            // Android  4.1以上
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                mUploadMessage = valueCallback;
                selectImage(WebViewActivity.this);
            }

            // Android 5.0以上
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                selectImage(WebViewActivity.this);
                return true;
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        } else if (requestCode == PICK_REQUEST_CODES && resultCode == Activity.RESULT_OK) {
            ArrayList<String> img = data.getStringArrayListExtra("data");
            String callbackMethod = data.getStringExtra("callbackMethod");
            if (!img.isEmpty()) {
                String imgs = img.get(0);
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
        } else if (requestCode == FILECHOOSER_RESULTCODE) {

            if (null == mUploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }

        } else if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (openEntity != null && account != null) {
                    mPresenter.doLogin2(openEntity, account, 1);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }

        } else  if (requestCode == ActivityRequestCode && data != null) {
            String response = data.getStringExtra("response");
            String message = data.getStringExtra("nativeSdkForMerchantMessage");
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response!=null?response:"{}");
            String status=    jsonObject.getString("STATUS");
                if (status!=null) {
                    Toast.makeText(
                            this,
                            status.replace("TXN_", ""),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

          //  Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();

        }

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILECHOOSER_RESULTCODE || uploadMessageAboveL == null) {
            uploadMessageAboveL.onReceiveValue(null);
            uploadMessageAboveL = null;
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
        uploadMessageAboveL = null;

    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void setOpenEntity(OpenEntity openEntity) {
        this.openEntity = openEntity;
    }

    @Override
    public void doLogin2(OpenEntity openEntity, GoogleSignInAccount account) {
        if (openEntity != null && account != null) {
            this.openEntity = openEntity;
            mPresenter.doLogin2(openEntity, account, 1);
        }
    }

    @Override
    public void doLogin2(Login data) {
        if (data.getToken1() != null) {
            CookieManager.getInstance().setCookie(openEntity.getHost(), "token1=" + data.getToken1() + ";expires=1; path=/");
        }
        if (data.getToken2() != null) {
            CookieManager.getInstance().setCookie(openEntity.getHost(), "token2=" + data.getToken2() + ";expires=1; path=/");
        }
        if (data.getUrl() != null) {
            webView.loadUrl(data.getUrl());
        }
    }

    @Override
    public void setShouldForbidBackPress(int data) {
        returns=data;
    }

    @Override
    public void setBackPressJSMethod(Object o) {
        BackPressJSMethod= (String) o;
    }

    @Override
    public void onBackPressed() {
        if (type==0){
            if (mPureBrowserEntity.isWebBack()){
                if (webView.canGoBack()){
                    webView.goBack(); // 后退
                }else {
                    super.onBackPressed();
                }
            }else {
                super.onBackPressed();
            }
        }else {
            if (returns==0){
                super.onBackPressed();
            }else {
                if (BackPressJSMethod!=null&&!"".equals(BackPressJSMethod)){
                    String javaScript = "javascript:" + BackPressJSMethod + "(" + ")";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        webView.evaluateJavascript(javaScript, null);
                    } else {
                        webView.loadUrl(javaScript);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        String url;
        switch (type){
            case 0:
                mPureBrowserEntity=   getIntent().getParcelableExtra("data");
                if (mPureBrowserEntity!=null){
                    setTitle(mPureBrowserEntity.getTitle());
                    mToolbar.setVisibility(mPureBrowserEntity.isHasTitleBar()?View.VISIBLE:View.GONE);
                    webView.loadUrl(mPureBrowserEntity.getUrl());
                    view_live.setVisibility(mPureBrowserEntity.isHasTitleBar()?View.VISIBLE:View.GONE);
                    if ("black".equals(mPureBrowserEntity.getStateBarTextColor())){
                        setStatusBarMode(this, true , Color.parseColor(mPureBrowserEntity.getTitleColor()));
                        // toolbar_title.setTextColor(Color.parseColor("#000000"));
                    }else {
                        setStatusBarMode(this, false , Color.parseColor(mPureBrowserEntity.getTitleColor()));
                    }
                    toolbar_title.setText(mPureBrowserEntity.getTitleTextColor());
                }

                break;
            case 1:
                 url = getIntent().getStringExtra("URL");
                if (url != null) {
                    webView.loadUrl(url);
                }
                break;
            case 2:
                 url = getIntent().getStringExtra("URL");
                paytmEntity=  getIntent().getParcelableExtra("data");
                if (url != null&&paytmEntity!=null) {
                    StringBuilder postData = new StringBuilder();
                    String postUrl =
                            url + "?mid=" + paytmEntity.getMid() + "&orderId=" + paytmEntity.getOrderId();
                    postData.append("MID=").append(paytmEntity.getMid())
                            .append("&txnToken=").append(paytmEntity.getTextToken())
                            .append("&AMOUNT=").append(paytmEntity.getAmount())
                            .append("&ORDER_ID=").append(paytmEntity.getOrderId());
                    webView.postUrl(postUrl, postData.toString().getBytes());
                }
                break;
        }
    }
}
