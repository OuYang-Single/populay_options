package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.Intent;
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
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerWaitComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerWebViewComponent;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.PaytmEntity;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.contract.WebViewContract;
import com.pine.populay_options.mvp.model.mvp.presenter.WaitPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.WebViewPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TradersAdapter;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.pine.populay_options.mvp.model.mvp.ui.Service.FileUtils.imageToBase64;
import static com.pine.populay_options.mvp.model.mvp.ui.activity.WaitActivity.selectImage;
import static com.wq.photo.widget.PickConfig.ActivityRequestCode;
import static com.wq.photo.widget.PickConfig.FILECHOOSER_RESULTCODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;

public class WebViewActivity extends BaseActivity<WebViewPresenter> implements WebViewContract.View, DownloadListener {
    @BindView(R.id.webview)
    WebView webView;
    androidx.appcompat.widget.Toolbar mToolbar;
    TextView toolbar_title;
    AppJs appJs;
    int type;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> uploadMessageAboveL;
    OpenEntity openEntity;
    PaytmEntity paytmEntity;
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
                        .append("&ORDER_ID=").append(paytmEntity.getOrderId());
                webView.postUrl(postUrl, postData.toString().getBytes());
            }
        }else {

        }
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.GONE);
        mPresenter.initWebSettings(webView.getSettings());
        appJs = new AppJs(this, webView);
        mPresenter.initWebView(webView, appJs);
        setTitle("");
        webView.setDownloadListener(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {

                    if (toolbar_title != null) {
                        toolbar_title.setText(title);
                    }

                }
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

}
