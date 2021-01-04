package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.mvp.model.di.component.DaggerAddDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.mvp.contract.AddDetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.presenter.AddDetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.DetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptDialog;


public class AddDetailsActivity extends BaseActivity<AddDetailsPresenter> implements AddDetailsContract.View{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.tab_text)
    EditText tab_text;
    @Inject
    PromptDialog promptDialog ;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_add_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.new_topic);
        mTvRightToolbar.setText(R.string.publish);
        mTvRightToolbar.setVisibility(View.VISIBLE);
        mToolbarBack.setVisibility(View.VISIBLE);
    }
    @Override
    public void showLoading() {
        promptDialog.showLoading("Loading...");
    }
    @Override
    public void hideLoading() {
        promptDialog.dismiss();
    }

    @OnClick({R.id.tv_right_toolbar})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_right_toolbar:
                mPresenter.add(tab_text.getText().toString());
                break;
        }
    }
    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        showMessage("添加成功");
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
