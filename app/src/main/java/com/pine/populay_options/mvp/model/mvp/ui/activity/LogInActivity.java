package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.pine.populay_options.R;

import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.presenter.LogInPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.utils.ArmsUtils;
import io.reactivex.annotations.NonNull;
import org.jetbrains.annotations.Nullable;

import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.jess.arms.utils.Preconditions.checkNotNull;


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
public class LogInActivity extends BaseActivity<LogInPresenter> implements LogInContract.View, IActivity, ActivityLifecycleable {

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

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
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



    @OnClick({R.id.log_txt_highlight, R.id.log_bt_log_in, R.id.log_linearl_other_forget_password_txt, R.id.log_linearl_other_registered_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.log_txt_highlight:
                break;
            case R.id.log_bt_log_in:
                mPresenter.login(logEditPhone.getText().toString(),logEditPassword.getText().toString());
                break;
            case R.id.log_linearl_other_forget_password_txt:
                break;
            case R.id.log_linearl_other_registered_txt:
                break;
        }
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
}
