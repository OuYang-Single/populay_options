package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.mvp.model.di.component.DaggerWaitComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.presenter.WaitPresenter;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.kinst.jakub.view.SimpleStatefulLayout;
import cz.kinst.jakub.view.StatefulLayout;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import me.leefeng.promptlibrary.PromptDialog;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.setStatusBarLightMode;
import static com.pine.populay_options.mvp.model.mvp.ui.Service.FileUtils.imageToBase64;
import static com.wq.photo.widget.PickConfig.ActivityRequestCode;
import static com.wq.photo.widget.PickConfig.FILECHOOSER_RESULTCODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;
import static cz.kinst.jakub.view.SimpleStatefulLayout.State.PROGRESS;

// setContentView(R.layout.activity_wait);
public class WaitActivity extends BaseActivity<WaitPresenter> implements WaitContract.View, DownloadListener, View.OnClickListener {
    String Tog=WaitActivity.class.getName();
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.advertising)
    ImageView advertising;

    @BindView(R.id.wait_time_jump_txt)
    TextView mWaitTimeJumpTxt;
    @BindView(R.id.wait_jump)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.view_live)
    View mView;
    @BindView(R.id.sfl_lyt_state)
    SimpleStatefulLayout statefulLayout;
    @Inject
    PromptDialog promptDialog ;
  /*  @BindView(R.id.sfl_lyt_state)
    StateFrameLayout lytState;*/
    OpenEntity openEntity;
    androidx.appcompat.widget.Toolbar mToolbar;
    TextView toolbar_title;
    ValueCallback<Uri> mUploadMessage;
    ValueCallback<Uri[]> uploadMessageAboveL;
    String URL = "";
    String BackPressJSMethod ;
    AppJs appJs;
    View activity_error;
    @Inject
    ImageLoader mImageLoader;
    TextView activity_error_text;
    Button tv_btn;
    int returns;

    @Inject
    StatefulLayout.StateController mStateController;
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
    protected void onStart() {
        super.onStart();
        if (data!=null){
            if ("black".equals(data.getFieldCol())){
                setStatusBarMode(WaitActivity.this, true ,Color.parseColor(data.getBackgroundCol()));
                toolbar_title.setTextColor(Color.parseColor("#000000"));
            }else {
                setStatusBarMode(WaitActivity.this, false ,Color.parseColor(data.getBackgroundCol()));
                toolbar_title.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }

    }
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).request();
       // statefulLayout.stateController(mStateController.getStates());
        statefulLayout.setStateController(mStateController);
 /*       activity_error=  getLayoutInflater().inflate(R.layout.activity_error, null, false);
        activity_error_img=   activity_error.findViewById(R.id.image);
        activity_error_text=   activity_error.findViewById(R.id.tv_content);
        tv_btn=   activity_error.findViewById(R.id.tv_btn);*/
      //  tv_btn.setOnClickListener(this);
       // lytState.setStateViews(getLayoutInflater().inflate(R.layout.custom_app_loading, null, false),activity_error,getLayoutInflater().inflate(R.layout.custom_network_error, null, false));
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
                    //lytState.normal();
                    promptDialog.dismiss();
                    statefulLayout.showContent();
                    if ("black".equals(data.getFieldCol())){
                        setStatusBarMode(WaitActivity.this, true ,Color.parseColor(data.getBackgroundCol()));
                        toolbar_title.setTextColor(Color.parseColor("#000000"));
                    }else {
                        setStatusBarMode(WaitActivity.this, false ,Color.parseColor(data.getBackgroundCol()));
                        toolbar_title.setTextColor(Color.parseColor("#FFFFFF"));
                    }
                   // mStateController.setState("");
                    String title = view.getTitle();
                    if (!TextUtils.isEmpty(title)) {

                        if (toolbar_title!=null){
                            toolbar_title.setText(title);
                        }

                    }
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
        mPresenter.vestSign(appJs);
        mToolbar.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
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

    @OnClick({R.id.wait_time_jump_txt,R.id.advertising})
    public void OnClick(View mView) {
        switch (mView.getId()) {
            case R.id.wait_time_jump_txt:
                if (data!=null){
                    if (data.getSta()==0){
                        mPresenter.Jumps();
                        Jumps();
                    }else {
                        mPresenter.Jump();
                    }
                }else {
                    mPresenter.Jump();
                }
                break;
            case R.id.advertising:
                if (data!=null){
                    if (data.getAdvOn()==1&&data.getAdvImg()!=null){
                        Intent  intent=new Intent(this, WebViewActivity.class);
                        intent.putExtra("type",1);
                        intent.putExtra("URL",data.getAdvUrl());
                        startActivity(intent);
                    }
                }
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
        this.data=data;
        if (data.getSta()==0){
            advertising.setVisibility(View.GONE);
            promptDialog.showLoading(getString(R.string.load));
            webView.setVisibility(View.VISIBLE);
            mRelativeLayout.setVisibility(View.GONE);
            webView.loadUrl(data.getHul());
            if (data.getBackgroundCol()!=null){
                try {
                    mToolbar.setBackgroundColor(Color.parseColor(data.getBackgroundCol()));
                }catch (Exception e){
                    Timber.i(Tog + " setBackgroundColor=="+e);
                }
            }
            if (data.isToolbar()){
                mToolbar.setVisibility(View.GONE);
                mView.setVisibility(View.GONE);
            }else {
                mToolbar.setVisibility(View.VISIBLE);
                mView.setVisibility(View.VISIBLE);
            }
            if (data.getAdvOn()==1&&data.getAdvImg()!=null){
             //   String url="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.bbra.cn%2F%28S%28cdh0n4ymxynm2bnschrajp55%29%29%2FUploadfiles%2Fimgs%2F2013%2F02%2F20%2Fmm2%2FXbzs_013.jpg&refer=http%3A%2F%2Fwww.bbra.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612284293&t=01b98a85533acbf538d61edec0a66235";
                mImageLoader.loadImage(this, ImageConfigImpl.builder().imageView(advertising).url(data.getAdvImg()).build());
                advertising.setVisibility(View.VISIBLE);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mPresenter.WaitingTime();
                webView.setVisibility(View.GONE);
                setStatusBarLightMode(getWindow());
               /*  private   String advImg;  //广告图片地址
                private   String advUrl;  //广告业务链接地址)*/
            } else {
                webView.setVisibility(View.VISIBLE);
                advertising.setVisibility(View.GONE);
                mRelativeLayout.setVisibility(View.GONE);
            }

        }else {
            statefulLayout.showContent();
            setFullscreen(this);
            webView.setVisibility(View.GONE);
            mPresenter.WaitingTime();
        }

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onError() {
       // activity_error_img

    }

    @Override
    public void setText(TextView viewById) {
        activity_error_text=viewById;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        //int img=R.mipmap.icon_network_error;
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
            mStateController.setState(NONETWORK_ERROR);
        }else {
            mStateController.setState(ERROR);
            if (activity_error_text!=null){
                activity_error_text.setText(message.messing);
            }
        }

      /*  activity_error_img.setImageResource(img);
        activity_error_text.setText(message.messing);*/

    }

    @Override
    public void showLoading() {
        advertising.setVisibility(View.VISIBLE);

    }
    @Override
    public void hideLoading() {

        statefulLayout.showContent();
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
            mView.setVisibility(visible?View.VISIBLE:View.GONE);
            webView.loadUrl(data.getHul());
        }
    }

    @Override
    public void setOpenEntity(OpenEntity openEntity) {
        this.openEntity=openEntity;
    }

    @Override
    public void doLogin2(OpenEntity openEntity, GoogleSignInAccount account) {
        if (openEntity != null && account != null) {
            this.openEntity = openEntity;
            mPresenter.doLogin2(openEntity, account, 1);
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

        }else if (requestCode == RC_SIGN_IN) {
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

    //重写onBackPressed方法


    @Override
    public void onBackPressed() {
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

    @Override
    public void onClick(View v) {
        mPresenter.vestSign(appJs);
    }

    @Override
    public boolean isJump() {

        return data.getSta()!=0;
    }

    @Override
    public void Jumps() {
        webView.setVisibility(View.VISIBLE);
        advertising.setVisibility(View.GONE);
        mRelativeLayout.setVisibility(View.GONE);
        if ("black".equals(data.getFieldCol())){
            setStatusBarMode(this, true ,Color.parseColor(data.getBackgroundCol()));
            toolbar_title.setTextColor(Color.parseColor("#000000"));
        }else {
            setStatusBarMode(this, false ,Color.parseColor(data.getBackgroundCol()));
            toolbar_title.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
