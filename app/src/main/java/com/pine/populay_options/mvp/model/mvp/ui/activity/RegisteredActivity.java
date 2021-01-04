package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptDialog;

import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;

@Route(path = "/analogDisk/RegisteredActivity")
public class RegisteredActivity extends BaseActivity<RegisteredPresenter> implements RegisteredContract.View {
    @BindView(R.id.log_edit_phone)
    EditText logEditPhone;
    @BindView(R.id.log_edit_password)
    EditText logEditPassword;
    @BindView(R.id.log_txt_highlight)
    TextView logTxtHighlight;
    @BindView(R.id.log_bt_log_in)
    Button logBtLogIn;
    @BindView(R.id.log_linearl_other_forget_password_txt)
    TextView logLinearlOtherForgetPasswordTxt;
    @BindView(R.id.log_linearl_other_registered_txt)
    TextView logLinearlOtherRegisteredTxt;
    @BindView(R.id.log_linearl_other)
    LinearLayout logLinearlOther;
    @Inject
    PromptDialog promptDialog ;
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

    }
    @OnClick({R.id.log_txt_highlight, R.id.log_bt_log_in, R.id.log_linearl_other_forget_password_txt, R.id.log_linearl_other_registered_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.log_txt_highlight:
                break;
            case R.id.log_bt_log_in:
                mPresenter.Registered(logEditPhone.getText().toString(),logEditPassword.getText().toString());
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
        promptDialog.showLoading("注册中...");
    }
    @Override
    public void hideLoading() {
        promptDialog.dismiss();
    }


    @Override
    public Activity getActivity() {
        return this;
    }
}
