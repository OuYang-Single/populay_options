package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.di.component.DaggerMineFragmentComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerPaperFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.MineFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.PaperFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.MineFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.PaperFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.ForexActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.app.utils.RxUtils.setFullscreen;
import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;

public class MineFragment extends BaseFragment<MineFragmentPresenter> implements MineFragmentContract.View{
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.txt_user_name)
    TextView txt_user_name;
    @Inject
    ManagerFactory mManagerFactory;
    AlertDialog alertDialog4 ;
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
       List<User>UserAll= mManagerFactory.getStudentManager(getContext()).queryAll();
        if (UserAll.size()>0){
            txt_user_name.setText(UserAll.get(0).getUsername());
        }
    }

    @OnClick({R.id.mine_assets,R.id.mine_top_brokers,R.id.mine_forex_videos,R.id.mine_most_famous_forex_traders,R.id.mine_study_forex,R.id.mine_tools,R.id.mine_customer_service,R.id.log_bt_log_out})
    public void OnClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.mine_assets:
                ARouter.getInstance().build("/analogDisk/position").navigation();
                break;
            case R.id.mine_top_brokers:
                ARouter.getInstance().build("/analogDisk/brokers").navigation();
                break;
            case R.id.mine_forex_videos:
                ARouter.getInstance().build("/analogDisk/videos").navigation();
                break;
            case R.id.mine_most_famous_forex_traders:
                ARouter.getInstance().build("/analogDisk/Traders").navigation();
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



    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()){
            setStatusBarMode(getActivity(), false , Color.parseColor("#2e343e"));
        }

    }

    @Override
    public void LogOut() {
        mManagerFactory.getStudentManager(getContext()).deleteAll();
        getAppManager().killAll();
        ARouter.getInstance().build("/analogDisk/LogInActivity").navigation();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
            showMessage(message.messing);
        }else {
            showMessage(message.messing);
        }
    }
}
