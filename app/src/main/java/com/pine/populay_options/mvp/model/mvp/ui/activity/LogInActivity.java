package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.textclassifier.TextClassifier;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pine.populay_options.R;

import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.presenter.LogInPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;

import net.rimoto.intlphoneinput.IntlPhoneInput;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.annotations.NonNull;
import me.leefeng.promptlibrary.PromptDialog;
import timber.log.Timber;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import static com.pine.populay_options.app.utils.DateUtil.isMobile;
import static com.pine.populay_options.app.utils.DateUtil.isNull;
import static com.pine.populay_options.app.utils.DateUtil.isPhone;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.pine.populay_options.mvp.model.di.module.WaitModule.TIMEJUMPTXT;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Route(path = "/analogDisk/LogInActivity")
public class LogInActivity extends BaseActivity<LogInPresenter> implements LogInContract.View, IActivity, ActivityLifecycleable {

    @BindView(R.id.log_edit_phone)
    IntlPhoneInput logEditPhone;
    @BindView(R.id.log_edit_password)
    EditText logEditPassword;

    @BindView(R.id.log_bt_log_in)
    Button logBtLogIn;
    @BindView(R.id.log_linearl_other_forget_password_txt)
    TextView logLinearlOtherForgetPasswordTxt;
    @BindView(R.id.log_linearl_other_registered_txt)
    TextView logLinearlOtherRegisteredTxt;
    @BindView(R.id.log_linearl_other)
    LinearLayout logLinearlOther;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.log_txt_highlight)
    TextView txtHighlight;
    @BindView(R.id.tv_right_toolbar)
    TextView tv_right_toolbar;

    @BindView(R.id.txt_mobile_phone_quick_login)
    TextView txt_mobile_phone_quick_login;

    @BindView(R.id.toolbar_back)
    RelativeLayout toolbar_back;

    int anInt=60;
    int Time = 1000;
    public int result=-2;
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                Timber.w(result + " SMSSDK.RESULT_COMPLETE");
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    LogInActivity. this. result=event;
                    Timber.w(result + " SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE");
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    Timber.w(result + " SMSSDK.EVENT_GET_VERIFICATION_CODE");
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Timber.w(result + " SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES");
                }
            }else{
                LogInActivity. this. result=-1;
                Timber.w(result + " printStackTrace");
                ((Throwable)data).printStackTrace();
            }
        }
    };

    @Inject
    PromptDialog promptDialog ;
    Runnable  mRunnable = new Runnable() {
        @Override
        public void run() {
            anInt--;
            Log.w("mRunnable", anInt + "");
            if (anInt != 0) {
                mHandler.postDelayed(mRunnable, Time);
            }
            Message message = new Message();
            message.what = TIMEJUMPTXT;
            message.arg1 = anInt;
            mHandler.sendMessage(message);
        }
    };
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TIMEJUMPTXT:
                    String TimeJumpTxt;
                    int arg1=msg.arg1;
                    TimeJumpTxt=getString(R.string.wait_resend);
                    if (arg1==0){
                        TimeJumpTxt=getString(R.string.get_cold);
                        anInt=60;
                    }else {
                        TimeJumpTxt=arg1+TimeJumpTxt;
                    }
                    if (txtHighlight!=null){
                        txtHighlight.setText(TimeJumpTxt+"");
                    }
                    break;
            }
        }
    };

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLogInComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("");
        toolbar_back.setVisibility(View.VISIBLE);
        tv_right_toolbar.setText(R.string.log_bt_registered_in);
        tv_right_toolbar.setVisibility(View.VISIBLE);
        text.post(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.width=  text.getWidth();
                layoutParams.height=  text.getHeight()/3;
                layoutParams.setMargins(0,text.getHeight()-text.getHeight()/3,0,0);
                view.setLayoutParams(layoutParams);
            }
        });
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    AlertDialog alertDialog4 ;
    @OnClick({R.id.log_txt_highlight, R.id.log_bt_log_in, R.id.log_linearl_other_forget_password_txt, R.id.log_linearl_other_registered_txt,R.id.txt_mobile_phone_quick_login,R.id.tv_right_toolbar})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.log_txt_highlight:
                if (isNull(logEditPhone.getText().toString())) {
                    showMessage(getString(R.string.log_in_account_null));
                    return ;
                }
                if (!(isMobile(logEditPhone.getPhone())||isPhone(logEditPhone.getPhone()))){
                    showMessage(getString(R.string.log_in_no_phone));
                    return ;
                }
                if (txtHighlight.getText().toString().equals(getString(R.string.get_cold))){
                    mPresenter.isUserExists(logEditPhone.getPhone(),logEditPhone.getDefaultRegion());
                }

                break;
            case R.id.log_bt_log_in:
                if (txtHighlight.getVisibility()==View.GONE){
                    mPresenter.login(logEditPhone.getPhone(),logEditPassword.getText().toString());
                }else {
                    mPresenter.codeLogin(logEditPhone.getPhone(),logEditPhone.getDefaultRegion(),logEditPassword.getText().toString());
                }

                break;
            case R.id.log_linearl_other_forget_password_txt:

                ARouter.getInstance().build("/analogDisk/ForgetPasswordActivity").navigation();
              /*  if (isNull(logEditPhone.getText().toString())) {
                    showMessage(getString(R.string.log_in_account_null));
                    return ;
                }
                if (!(isMobile(Name)||isPhone(Name))){
                   showMessage(getString(R.string.log_in_no_phone));
                    return ;
                }
                alertDialog4 = new AlertDialog.Builder(this)
                        .setMessage(R.string.log_outs)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.password(Name);
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog4.dismiss();
                            }
                        })
                        .create();*/
                //alertDialog4.show();
                break;
            case R.id.txt_mobile_phone_quick_login:
                if (txtHighlight.getVisibility()==View.GONE){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        logEditPassword.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    }
                    txtHighlight.setVisibility(View.VISIBLE);
                    logLinearlOtherForgetPasswordTxt.setVisibility(View.GONE);
                    txt_mobile_phone_quick_login.setText(R.string.password_login);
                    logEditPassword.setHint(R.string.verification_code);
                }else {
                    logEditPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    logEditPassword.setHint(R.string.code);
                    logLinearlOtherForgetPasswordTxt.setVisibility(View.VISIBLE);
                    txtHighlight.setVisibility(View.GONE);
                    txt_mobile_phone_quick_login.setText(R.string.Mobile_phone_quick_login);
                }

                break;
            case R.id.tv_right_toolbar:
                ARouter.getInstance().build("/analogDisk/RegisteredActivity").navigation();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        //int img=R.mipmap.icon_network_error;
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
            showMessage(message.messing);
        }else {
            showMessage(message.messing);
        }
    }
    @Override
    public void showLoading() {
        promptDialog.showLoading(getString(R.string.load));
    }
    @Override
    public void hideLoading() {
        promptDialog.dismiss();
    }

    @Override
    public Context getContent() {
        return this;
    }

    @Override
    public void statusService(Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh); //
    }

    @Override
    public void getCode() {
        mHandler.postDelayed(mRunnable, Time);
    }

    @Override
    public void initResult() {
        result=-2;
    }

    @Override
    public int getResult() {
        return result;
    }
}
