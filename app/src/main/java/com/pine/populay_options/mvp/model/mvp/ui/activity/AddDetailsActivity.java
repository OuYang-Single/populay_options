package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.di.component.DaggerAddDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.entity.User;
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

import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
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
    @Inject
    ManagerFactory mManagerFactory;
    Long UserId = -1L;
    @BindView(R.id.check_box)
    CheckBox check_box;
    @BindView(R.id.LinearLayout)
    LinearLayout mLinearLayout;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAddDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    SharedPreferences sp;
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
        List<User> users = mManagerFactory.getStudentManager(this).queryAll();
        if (users != null && users.size() > 0) {
            UserId = users.get(0).getId();
        }
        //可以创建一个新的SharedPreference来对储存的文件进行操作
         sp=getSharedPreferences("AddDetailsActivity", Context.MODE_PRIVATE);
//像SharedPreference中写入数据需要使用Editor
        SharedPreferences.Editor editor = sp.edit();
        int age=sp.getInt("age", 0);
//类似键值对

        editor.commit();
        mLinearLayout.setVisibility(View.VISIBLE);
        if (age==0){

            if (check_box.isChecked()){
                mTvRightToolbar.setTextColor(ResourcesUtils.getColorStateList(AddDetailsActivity.this,R.color.black));
                mTvRightToolbar.setEnabled(true);
            }else {
                mTvRightToolbar.setTextColor( ResourcesUtils.getColorStateList(AddDetailsActivity.this,R.color.gray));
                mTvRightToolbar.setEnabled(false);
            }
        }else {
         //   mLinearLayout.setVisibility(View.GONE);
        }

        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check_box.isChecked()){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("age", 1);
                    editor.commit();
                    mTvRightToolbar.setTextColor(ResourcesUtils.getColorStateList(AddDetailsActivity.this,R.color.black));
                    mTvRightToolbar.setEnabled(true);
                }else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("age", 0);
                    editor.commit();
                    mTvRightToolbar.setTextColor( ResourcesUtils.getColorStateList(AddDetailsActivity.this,R.color.gray));
                    mTvRightToolbar.setEnabled(false);
                }
            }
        });
        findViewById(R.id.txt_registration_agreement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=APP_DOMAIN+AppDomain+"/Content.html";
                Intent      intent=new Intent(AddDetailsActivity.this, WebViewActivity.class);
                intent.putExtra("type",3);
                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });
    }
    @Override
    public void showLoading() {
        promptDialog.showLoading("Loading...");
    }
    @Override
    public void hideLoading() {
        promptDialog.dismiss();
    }
    AlertDialog    alertDialog4;
    @OnClick({R.id.tv_right_toolbar})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_right_toolbar:
                if (UserId == -1) {
                        alertDialog4 = new AlertDialog.Builder(this)
                            .setMessage(R.string.to_login)
                            .setPositiveButton(R.string.Go_to_login, new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ARouter.getInstance().build("/analogDisk/LogInActivity").navigation();
                                }
                            })

                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog4.dismiss();
                                }
                            }).create();
                    alertDialog4.show();
                    return;
                }else {

                    if ("".equals(tab_text.getText().toString())){
                        Toast.makeText(this, R.string.no, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (strings.size()>0){
                        mPresenter.add(tab_text.getText().toString(),strings);
                    }else {
                        mPresenter.adds(tab_text.getText().toString());
                    }
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
