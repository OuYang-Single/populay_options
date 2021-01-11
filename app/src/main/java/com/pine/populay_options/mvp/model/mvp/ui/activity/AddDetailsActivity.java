package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
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
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ImageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptDialog;

import static com.pine.populay_options.mvp.model.mvp.ui.Service.FileUtils.imageToBase64;
import static com.wq.photo.widget.PickConfig.ActivityRequestCode;
import static com.wq.photo.widget.PickConfig.FILECHOOSER_RESULTCODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;
import static com.wq.photo.widget.PickConfig.RC_SIGN_IN;


public class AddDetailsActivity extends BaseActivity<AddDetailsPresenter> implements AddDetailsContract.View{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.tab_text)
    EditText tab_text;
    @BindView(R.id.img_grid)
    GridView img_grid;
    @Inject
    ImageAdapter imageAdapter;
    @Inject
    List<String> strings;
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
        img_grid.setAdapter(imageAdapter);
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
                if (strings.size()>0){
                    mPresenter.add(tab_text.getText().toString(),strings);
                }else {
                    mPresenter.adds(tab_text.getText().toString());
                }

                break;
        }
    }
    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        showMessage(getString(R.string.successd));
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

        }else if (requestCode == PICK_REQUEST_CODES && resultCode == Activity.RESULT_OK){
            ArrayList<String> img = data.getStringArrayListExtra("data");
            if (!img.isEmpty()){
                strings.clear();
                strings.addAll(img);
                imageAdapter.notifyDataSetChanged();
            }
        }
    }

}
