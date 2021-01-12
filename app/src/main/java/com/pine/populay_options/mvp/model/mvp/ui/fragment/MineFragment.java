package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.di.component.DaggerMineFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.ImageInfo;
import com.pine.populay_options.mvp.model.entity.LoginEvent;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.MineFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForexActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity2;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import m.zz.zzlibrary.widget.BottomShareMenu;
import me.leefeng.promptlibrary.PromptDialog;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.app.utils.CleanDataUtils.clearAllCache;
import static com.pine.populay_options.app.utils.CleanDataUtils.getTotalCacheSize;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
import static com.pine.populay_options.mvp.model.api.Api.file;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODE;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;

public class MineFragment extends BaseFragment<MineFragmentPresenter> implements MineFragmentContract.View {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.txt_user_name)
    TextView txt_user_name;
    @BindView(R.id.like)
    TextView like;
    @BindView(R.id.release)
    TextView release;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.clear_cache_text)
    TextView clear_cache_text;
    @BindView(R.id.log_bt_log_out)
    Button log_bt_log_out;
    @BindView(R.id.neo)
    CircularImageView mCircularImageView;
    @Inject
    ManagerFactory mManagerFactory;
    AlertDialog alertDialog4;
    List<User> UserAll;
    String img;
    @Inject
    PromptDialog mPromptDialog;
    @Inject
    ImageLoader mImageLoader;
    BottomShareMenu  bottomShareMenu;
    View mButtonView;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return getLayoutInflater().inflate(R.layout.fragment_mine, null, false);

    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.Mine);
        clear_cache_text.setText(getTotalCacheSize(getContext()));
        onLogIn(null);
        bottomShareMenu = new BottomShareMenu(getContext()) {
            @Override
            protected View onBindView() {
                mButtonView = getLayoutInflater().inflate(R.layout.bottom_topics_views, null);
                return mButtonView;
            }

            @Override
            protected void setData() {
                if (mButtonView!=null){
                    LinearLayout bottom_topics_view_shield=    mButtonView.findViewById(R.id.bottom_topics_view_shield);
                    LinearLayout bottom_topics_view_complaint=    mButtonView.findViewById(R.id.bottom_topics_view_complaint);
                    LinearLayout bottom_topics_view_cancel=     mButtonView.findViewById(R.id.bottom_topics_view_cancel);
                    bottom_topics_view_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomShareMenu.dismiss();
                        }
                    });
                    bottom_topics_view_complaint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            zn(getContext());
                            bottomShareMenu.dismiss();
                        }
                    });
                    bottom_topics_view_shield.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            en(getContext());
                            bottomShareMenu.dismiss();
                        }
                    });
                }


            }
        };
    }
    /*showLoading*/

    @Override
    public void showLoading() {
        mPromptDialog.showLoading("Loading ...");
    }

    @Override
    public void hideLoading() {
        mPromptDialog.dismiss();
    }

    @OnClick({R.id.mine_assets, R.id.mine_top_brokers, R.id.mine_forex_videos, R.id.mine_most_famous_forex_traders, R.id.mine_study_forex, R.id.mine_tools, R.id.mine_customer_service, R.id.log_bt_log_out, R.id.line_mine})
    public void OnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.mine_assets:
                ARouter.getInstance().build("/analogDisk/position").navigation();
                break;
            case R.id.mine_top_brokers:
              //  bottomShareMenu.show();

               // ARouter.getInstance().build("/analogDisk/brokers").navigation();
                break;
            case R.id.mine_forex_videos:
                ARouter.getInstance().build("/analogDisk/videos").navigation();
                break;
            case R.id.mine_most_famous_forex_traders:
               // ARouter.getInstance().build("/analogDisk/Traders").navigation();
                mPromptDialog.showLoading("Loading ...");
                clearAllCache(getContext());
                mPromptDialog.dismiss();
                Toast.makeText(mContext, R.string.Cleared_successfully, Toast.LENGTH_SHORT).show();
                clear_cache_text.setText(getTotalCacheSize(getContext()));
                break;
            case R.id.mine_study_forex:
                ARouter.getInstance().build("/analogDisk/Forex").navigation();
                break;
            case R.id.mine_tools:
                ARouter.getInstance().build("/analogDisk/tools").navigation();
                break;
            case R.id.mine_customer_service:
                ARouter.getInstance().build("/analogDisk/customer_service").navigation();
                break;
            case R.id.line_mine:
                if (UserAll.size() > 0) {
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
                            .isneedcrop(true)//受否需要剪裁
                            .setUropOptions(options) //设置剪裁参数
                            .isSqureCrop(true) //是否是正方形格式剪裁
                            .requestCode(PICK_REQUEST_CODES)
                            .pickMode(PickConfig.MODE_SINGLE_PICK)//单选还是多选
                            .callbackMethod("")
                            .build();
                } else {
                    ARouter.getInstance().build("/analogDisk/LogInActivity").navigation();
                }

                break;
            case R.id.log_bt_log_out:

                alertDialog4 = new AlertDialog.Builder(getContext())
                        .setMessage(R.string.log_out)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.logOut();
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog4.dismiss();
                            }
                        })
                        .create();
                alertDialog4.show();
                break;
        }
    }
    /**
     * 设置英文
     *
     * @param v
     */
    public void en(Context v) {
        Configuration config = getResources().getConfiguration();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        config.locale = Locale.ENGLISH;
        getResources().updateConfiguration(config, metrics);
    }

    /**
     * 设置中文
     *
     * @param v
     */
    public void zn(Context v) {

       /* Configuration config = getResources().getConfiguration();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        config.locale = Locale.SIMPLIFIED_CHINESE;
        getResources().updateConfiguration(config, metrics);*/

        Resources resources = mContext.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config= resources.getConfiguration();

        Locale locale = Locale.SIMPLIFIED_CHINESE;;// getSetLocale方法是获取新设置的语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        resources.updateConfiguration(config, dm);
    }
        @Override
    public void setData(@Nullable Object data) {

    }
    public void setdefaultLanguage(Context context, String languageToLoad) {
        //String languageToLoad  = "zh";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        config.locale = Locale.SIMPLIFIED_CHINESE;
        getResources().updateConfiguration(config, metrics);
    }
    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()) {
            setStatusBarMode(getActivity(), false, Color.parseColor("#2e343e"));
        }
    }

    @Override
    public void LogOut() {
        mManagerFactory.getStudentManager(getContext()).deleteAll();
        onLogIn(null);
      //  getAppManager().killAll();
      //  ARouter.getInstance().build("/analogDisk/LogInActivity").navigation();

    }

    @Override
    public void modifyAvatar(User data) {
        onLogIn(null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
            showMessage(message.messing);
        } else {
            showMessage(message.messing);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onImageInfoEvent(ImageInfo message) {
        mPresenter.modifyAvatar(message.getImg(), UserAll.get(0).getId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogIn(LoginEvent message) {
        UserAll = mManagerFactory.getStudentManager(getContext()).queryAll();
        if (UserAll.size() > 0) {
            txt_user_name.setText(UserAll.get(0).getUsername());
            mImageLoader.loadImage(getContext(), ImageConfigImpl.builder().imageView(mCircularImageView).url(APP_DOMAIN+AppDomain+file+UserAll.get(0).getAvatar()).placeholder(R.mipmap.img_avatar).errorPic(R.mipmap.img_avatar).build());
           // like comment release
                    if(UserAll.get(0).getQuestion()!=null&&!"0".equals(UserAll.get(0).getQuestion())&&!"".equals(UserAll.get(0).getQuestion())){
                        comment.setText(UserAll.get(0).getQuestion());
                    }else {
                        comment.setText("-");
                    }
            if(UserAll.get(0).getAnswer()!=null&&!"0".equals(UserAll.get(0).getAnswer())&&!"".equals(UserAll.get(0).getAnswer())){
                like.setText(UserAll.get(0).getAnswer());
            }else {
                like.setText("-");
            }
            if(UserAll.get(0).getRole()!=0){
                release.setText(UserAll.get(0).getAnswer());
            }else {
                release.setText("-");
            }
            log_bt_log_out.setVisibility(View.VISIBLE);
        }else {
            log_bt_log_out.setVisibility(View.GONE);
            txt_user_name.setText(R.string.mine_string);
            like.setText("-");
            release.setText("-");
            comment.setText("-");
            mImageLoader.loadImage(getContext(), ImageConfigImpl.builder().imageView(mCircularImageView).url("").placeholder(R.mipmap.img_avatar).errorPic(R.mipmap.img_avatar).build());
        }

    }


}
