package com.pine.populay_options.mvp.model.mvp.ui.Service;

import android.Manifest;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.IBinder;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;

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
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WaitActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WebViewActivity;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;
import timber.log.Timber;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.mvp.model.entity.BranchEvent.CALLBACKMETHOD;
import static com.pine.populay_options.mvp.model.wigth.chatkit.utils.Paytm.checkApkExist;
import static com.pine.populay_options.mvp.model.wigth.chatkit.utils.Paytm.goToPaytm;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;


public class BranchEventService extends BaseService {

    @Inject
    Application mApplication;

    @Override
    public void init() {

    }

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
                        .isneedcamera(true)//是否需要第一项是相机
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
        }
    }
    // OnClick
}
