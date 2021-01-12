package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.di.component.DaggerPositionComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerRegisteredComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.mvp.contract.RegisteredContract;
import com.pine.populay_options.mvp.model.mvp.presenter.RegisteredPresenter;

import net.rimoto.intlphoneinput.IntlPhoneInput;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import me.leefeng.promptlibrary.PromptDialog;
import timber.log.Timber;

import static com.pine.populay_options.app.utils.DateUtil.isMobile;
import static com.pine.populay_options.app.utils.DateUtil.isNull;
import static com.pine.populay_options.app.utils.DateUtil.isPhone;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
import static com.pine.populay_options.mvp.model.di.module.WaitModule.TIMEJUMPTXT;

@Route(path = "/analogDisk/RegisteredActivity")
public class RegisteredActivity extends BaseActivity<RegisteredPresenter> implements RegisteredContract.View {
    @BindView(R.id.log_edit_phone)
    IntlPhoneInput logEditPhone;
    @BindView(R.id.log_edit_password)
    EditText logEditPassword;
    @BindView(R.id.log_txt_highlight)
    TextView txtHighlight;
    @BindView(R.id.log_bt_log_in)
    Button logBtLogIn;
    @BindView(R.id.log_linearl_other_forget_password_txt)
    TextView logLinearlOtherForgetPasswordTxt;
    @BindView(R.id.log_linearl_other_registered_txt)
    TextView logLinearlOtherRegisteredTxt;
    @BindView(R.id.log_linearl_other)
    LinearLayout logLinearlOther;
    @BindView(R.id.txt_registered)
    TextView txt_registered;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.log_edit_code)
    TextView log_edit_code;
    @BindView(R.id.check_box)
    CheckBox check_box;
    public int result=-2;
    EventHandler eh=new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                Timber.w(result + " SMSSDK.RESULT_COMPLETE");
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    RegisteredActivity. this. result=event;
                    Timber.w(result + " SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE");
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    Timber.w(result + " SMSSDK.EVENT_GET_VERIFICATION_CODE");
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Timber.w(result + " SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES");
                }
            }else{
                RegisteredActivity. this. result=-1;
                Timber.w(result + " printStackTrace");
                ((Throwable)data).printStackTrace();
            }
        }
    };
    @Inject
    PromptDialog promptDialog ;
    int anInt=60;
    int Time = 1000;
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
                        TimeJumpTxt=TimeJumpTxt +arg1;
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
        DaggerRegisteredComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.registered_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("");
        SMSSDK.registerEventHandler(eh); //注册短信回调
        findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
        log_edit_code=(TextView) findViewById(R.id.log_edit_code);
        if (txt_registered==null){
            txt_registered= (TextView) findViewById(R.id.txt_registered);
            view= (TextView) findViewById(R.id.view);
            check_box= (CheckBox) findViewById(R.id.check_box);
        }
        txt_registered.post(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) view.getLayoutParams();
                layoutParams.width=  txt_registered.getWidth();
                layoutParams.height=  txt_registered.getHeight()/3;
                layoutParams.setMargins(0,txt_registered.getHeight()-txt_registered.getHeight()/3,0,0);
                view.setLayoutParams(layoutParams);
            }
        });
        if (check_box.isChecked()){
            logBtLogIn.setBackgroundResource(R.drawable.log_bt_log_in_bg);
            logBtLogIn.setEnabled(true);
        }else {
            logBtLogIn.setBackgroundResource(R.drawable.log_bt_log_in_bg_s);
            logBtLogIn.setEnabled(false);
        }
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    logBtLogIn.setBackgroundResource(R.drawable.log_bt_log_in_bg);
                    logBtLogIn.setEnabled(true);
                }else {
                    logBtLogIn.setBackgroundResource(R.drawable.log_bt_log_in_bg_s);
                    logBtLogIn.setEnabled(false);
                }
            }
        });
        findViewById(R.id.txt_registration_agreement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=APP_DOMAIN+AppDomain+"/dsds.html";
                Intent      intent=new Intent(RegisteredActivity.this, WebViewActivity.class);
                intent.putExtra("type",3);
                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });
    }
    @OnClick({R.id.log_txt_highlight, R.id.log_bt_log_in, R.id.log_linearl_other_forget_password_txt, R.id.log_linearl_other_registered_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.log_txt_highlight:
                if (isNull(logEditPhone.getNumber())) {
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

                mPresenter.Registered(logEditPhone.getPhone(),logEditPhone.getDefaultRegion(),logEditPassword.getText().toString(),log_edit_code.getText().toString());
                break;
            case R.id.log_linearl_other_forget_password_txt:
                break;
            case R.id.log_linearl_other_registered_txt:
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        //int img=R.mipmap.icon_network_error;
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
           showMessage(message.messing);
           fileList();
        }else {
            showMessage("注册失败");

        }
    }
    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh); //
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void getCode() {
        mHandler.postDelayed(mRunnable, Time);
    }

    @Override
    public Integer getResult() {
        return result;
    }

    @Override
    public void initResult() {
        result=-2;
    }
}
