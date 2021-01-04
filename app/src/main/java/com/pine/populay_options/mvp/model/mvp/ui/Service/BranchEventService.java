package com.pine.populay_options.mvp.model.mvp.ui.Service;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.jess.arms.base.BaseService;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.BranchEvent;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.PaytmEntity;
import com.pine.populay_options.mvp.model.entity.PureBrowserEntity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WaitActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WebViewActivity;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.MethodUtil;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.mvp.model.entity.BranchEvent.CALLBACKMETHOD;
import static com.pine.populay_options.mvp.model.wigth.chatkit.utils.Paytm.checkApkExist;
import static com.pine.populay_options.mvp.model.wigth.chatkit.utils.Paytm.goToPaytm;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;


public class BranchEventService extends BaseService {

    @Inject
    Application mApplication;

    @Override
    public void init() {

    }
 /*=pYux9/rkg9a*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(BranchEvent message) {
        switch (message.getEventName()) {
            case ShowTitleBarEVent:
                View view = getAppManager().getCurrentActivity().findViewById(R.id.toolbar);
                if (view != null) {
                    view.setVisibility(((Boolean) message.getData()) ? View.VISIBLE : View.GONE);
                }
                break;
            case takePortraitPicture:

                Map<String, String> map = ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson((String) message.getData(), HashMap.class);
                //图片剪裁的一些设置
                UCrop.Options options = new UCrop.Options();
                //图片生成格式
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                //图片压缩比
                options.setCompressionQuality(2);
                new PickConfig.Builder(getAppManager().getCurrentActivity())
                        .maxPickSize(1)//最多选择几张
                        .isneedcamera(false)//是否需要第一项是相机
                        .spanCount(4)//一行显示几张照片
                        .actionBarcolor(Color.parseColor("#ffffff"))//设置toolbar的颜色
                        .statusBarcolor(Color.parseColor("#ffffff")) //设置状态栏的颜色(5.0以上)
                        .isneedcrop(false)//受否需要剪裁
                        .setUropOptions(options) //设置剪裁参数
                        .isSqureCrop(true) //是否是正方形格式剪裁
                        .requestCode(PICK_REQUEST_CODES)
                        .pickMode(PickConfig.MODE_SINGLE_PICK)//单选还是多选
                        .callbackMethod(map.get(CALLBACKMETHOD))
                        .build();
                // getAppManager().getCurrentActivity().
                break;
            case openGoogle:
                OpenEntity openEntity = ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson((String) message.getData(), OpenEntity.class);
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                if (getAppManager().getCurrentActivity() instanceof WaitActivity) {
                    ((WaitActivity) getAppManager().getCurrentActivity()).setOpenEntity(openEntity);
                } else if (getAppManager().getCurrentActivity() instanceof WebViewActivity) {
                    ((WebViewActivity) getAppManager().getCurrentActivity()).setOpenEntity(openEntity);
                }
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                //updateUI(account);
                if (account == null) {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    getAppManager().getCurrentActivity().startActivityForResult(signInIntent, RC_SIGN_IN);
                } else {
                    if (getAppManager().getCurrentActivity() instanceof WaitActivity) {
                        ((WaitActivity) getAppManager().getCurrentActivity()).doLogin2(openEntity, account);
                    } else if (getAppManager().getCurrentActivity() instanceof WebViewActivity) {
                        ((WebViewActivity) getAppManager().getCurrentActivity()).doLogin2(openEntity, account);
                    }
                }
                break;
            case openPayTm:
                PaytmEntity paytmEntity = (PaytmEntity) message.getData();
                if (checkApkExist(this, "net.one97.paytm")) {
                    goToPaytm(getAppManager().getCurrentActivity(), paytmEntity.getAmount(), paytmEntity.getOrderId(), paytmEntity.getTextToken(), paytmEntity.getMid());
                } else {
                    Intent intent = new Intent(getAppManager().getCurrentActivity(), WebViewActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("URL", "https://securegw.paytm.in/theia/api/v1/showPaymentPage");
                    intent.putExtra("data", paytmEntity);
                    getAppManager().getCurrentActivity().  startActivity(intent);
                }
                break;
            case isContainsName:
                Map maps= ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson(message.getData().toString(),Map.class);
                String callbackMethod= (String) maps.get(CALLBACKMETHOD);
               String name= (String) maps.get( BranchEvent.NAME);
                boolean has = false;
                Method[] methods=   new  MethodUtil().getClassMethods(AppJs.class);
                for (int i=0;i<methods.length;i++){
                    if (methods[i].getName().equals(name)  ) {
                        has=true;
                    }
                }

                WebView webView =  getAppManager().getCurrentActivity().findViewById(R.id.webview);;
                String javaScript = "javascript:" + callbackMethod + "(" + has + ")";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(javaScript, null);
                } else {
                    webView.loadUrl(javaScript);
                }

               // ArmsUtils.obtainAppComponentFromContext(mContext).gson().toJson(map);
                break;
            case shouldForbidSysBackPress:
                if (getAppManager().getCurrentActivity() instanceof WaitActivity) {
                    ((WaitActivity) getAppManager().getCurrentActivity()).setShouldForbidBackPress((Integer) message.getData());
                } else if (getAppManager().getCurrentActivity() instanceof WebViewActivity) {
                    ((WebViewActivity) getAppManager().getCurrentActivity()).setShouldForbidBackPress((Integer) message.getData());
                }
                break;

            case forbidBackForJS:
                Map<String ,Object >forbidBackForJS= (Map<String, Object>) message.getData();
                if (getAppManager().getCurrentActivity() instanceof WaitActivity) {
                    ((WaitActivity) getAppManager().getCurrentActivity()).setShouldForbidBackPress((Integer) forbidBackForJS.get(BranchEvent.FORBID));
                    ((WaitActivity) getAppManager().getCurrentActivity()).setBackPressJSMethod(forbidBackForJS.get(CALLBACKMETHOD));
                } else if (getAppManager().getCurrentActivity() instanceof WebViewActivity) {
                    ((WebViewActivity) getAppManager().getCurrentActivity()).setShouldForbidBackPress((Integer) forbidBackForJS.get(BranchEvent.FORBID));
                    ((WebViewActivity) getAppManager().getCurrentActivity()).setBackPressJSMethod(forbidBackForJS.get(CALLBACKMETHOD));
                }
                break;
            case openPureBrowser:
                PureBrowserEntity mPureBrowserEntity= ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson((String) message.getData(),PureBrowserEntity.class);
                Intent intent = new Intent(getAppManager().getCurrentActivity(), WebViewActivity.class);
                intent.putExtra("data", mPureBrowserEntity);
                getAppManager().getCurrentActivity().startActivity(intent);
                break;
        }
    }
    // OnClick
}
