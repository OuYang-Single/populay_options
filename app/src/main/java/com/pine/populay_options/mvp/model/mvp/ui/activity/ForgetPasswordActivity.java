package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.mvp.model.di.component.DaggerForgetPasswordComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.presenter.ForgetPasswordPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.LogInPresenter;

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
import static com.pine.populay_options.mvp.model.di.module.WaitModule.TIMEJUMPTXT;

@Route(path = "/analogDisk/ForgetPasswordActivity")
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View{
    @BindView(R.id.toolbar_back)
    RelativeLayout rLayoutBack;
    @BindView(R.id.txt_registered)
    TextView txt_registered;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.log_edit_phone)
    IntlPhoneInput mEditPhone;
    @BindView(R.id.log_edit_code)
    EditText mEditCode;
    @BindView(R.id.log_edit_password)
    EditText mEditPassword;
    @BindView(R.id.log_edit_new_password)
    EditText mEditNewPassword;
    @BindView(R.id.log_bt_log_in)
    Button mLogIn;
    @BindView(R.id.log_txt_highlight)
    TextView mTxtHighLight;
    @Inject
    PromptDialog promptDialog ;
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
                    ForgetPasswordActivity. this. result=event;
                    Timber.w(result + " SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE");
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    Timber.w(result + " SMSSDK.EVENT_GET_VERIFICATION_CODE");
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Timber.w(result + " SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES");
                }
            }else{
                ForgetPasswordActivity. this. result=-1;
                Timber.w(result + " printStackTrace");
                ((Throwable)data).printStackTrace();
            }
        }
    };
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
                    if (mTxtHighLight!=null){
                        mTxtHighLight.setText(TimeJumpTxt+"");
                    }
                    break;
            }
        }
    };
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerForgetPasswordComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
           return R.layout.activity_forget_password; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("");
        rLayoutBack.setVisibility(View.VISIBLE);
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
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @OnClick({R.id.log_txt_highlight,R.id.log_bt_log_in})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.log_txt_highlight:
                if (isNull(mEditPhone.getText().toString())) {
                    showMessage(getString(R.string.log_in_account_null));
                    return ;
                }
                if (!(isMobile(mEditPhone.getPhone())||isPhone(mEditPhone.getPhone()))){
                    showMessage(getString(R.string.log_in_no_phone));
                    return ;
                }
                if (mTxtHighLight.getText().toString().equals(getString(R.string.get_cold))){
                  //  mHandler.postDelayed(mRunnable, Time);
                    mPresenter.isUserExists(mEditPhone.getPhone(),mEditPhone.getDefaultRegion());
                }
                break;
            case R.id.log_bt_log_in:
                if (isNull(mEditPhone.getText().toString())) {
                    showMessage(getString(R.string.log_in_account_null));
                    return ;
                }
                if (!(isMobile(mEditPhone.getPhone())||isPhone(mEditPhone.getPhone()))){
                    showMessage(getString(R.string.log_in_no_phone));
                    return ;
                }
                if (isNull(mEditCode.getText().toString())) {
                    showMessage(getString(R.string.log_in_cold_null));
                    return ;
                }
                if (isNull(mEditPassword.getText().toString())) {
                    showMessage(getString(R.string.log_in_cold_nulls));
                    return ;
                }
                if (isNull(mEditNewPassword.getText().toString())) {
                    showMessage(getString(R.string.log_in_cold_nullss));
                    return ;
                }

                if (!mEditPassword.getText().toString().equals(mEditNewPassword.getText().toString())) {
                    showMessage(getString(R.string.log_in_password_nulls));
                    return ;
                }
                mPresenter.changePassword(mEditPhone.getPhone(),mEditPhone.getDefaultRegion(),mEditCode.getText().toString(),mEditNewPassword.getText().toString());
                break;
        }
    }
   // changePassword(String phone,String defaultRegion,String code,String password)
    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCode() {
        mHandler.postDelayed(mRunnable, Time);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Integer getResult() {
        return result;
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
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh); //
    }

    @Override
    public void killMyself() {
        finish();
    }
    @Override
    public void initResult() {
        result=-2;
    }
}
