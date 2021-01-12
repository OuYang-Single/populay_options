package com.pine.populay_options.mvp.model.mvp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.pine.populay_options.R;
import com.pine.populay_options.app.ResponseErrorListenerImpl;
import com.pine.populay_options.app.utils.StatusBarUtil;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.di.component.DaggerTopicsFragmentComponent;
import com.pine.populay_options.mvp.model.entity.ErrorEntity;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TopicsFragmentPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.activity.AddDetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DemoTradingActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;
import com.pine.populay_options.mvp.model.mvp.ui.activity.MainActivity2;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.kinst.jakub.view.SimpleStatefulLayout;
import cz.kinst.jakub.view.StatefulLayout;
import m.zz.zzlibrary.widget.BottomShareMenu;
import me.leefeng.promptlibrary.PromptDialog;

import static com.pine.populay_options.app.utils.StatusBarUtil.setStatusBarMode;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NODATA;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;


public class TopicsFragment extends BaseFragment<TopicsFragmentPresenter> implements TopicsFragmentContract.View, DefaultAdapter.OnRecyclerViewItemClickListener, OnLoadmoreListener, OnRefreshLoadmoreListener, TopicsAdapter.onClickTopicsItmeAdListener {
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_right_toolbar)
    TextView mTvRightToolbar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.sfl_lyt_state)
    SimpleStatefulLayout sfl_lyt_state;
    @Inject
    StatefulLayout.StateController mStateController;
    @Inject
    TopicsAdapter mTopicsAdapter;
    int pageNum = 1;
    TextView activity_error_text;
    @Inject
    ManagerFactory mManagerFactory;
    Long UserId = -1L;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    PromptDialog mPromptDialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSmartRefreshLayout.autoRefresh();
        }
    };
    BottomShareMenu bottomShareMenu;

    View mButtonView;
    Topics topics;
    int position;
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerTopicsFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutInflater().inflate(R.layout.fragment_topics, null, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarTitle.setText(R.string.Topics);
        sfl_lyt_state.setStateController(mStateController);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTopicsAdapter.setImageLoader(mImageLoader);
        mRecyclerView.setAdapter(mTopicsAdapter);

        mTopicsAdapter.setOnClickListener(this);
        mTopicsAdapter.setOnItemClickListener(this);
        mSmartRefreshLayout.setOnRefreshLoadmoreListener(this);
        bottomShareMenu = new BottomShareMenu(getContext()) {
            @Override
            protected View onBindView() {
                mButtonView = getLayoutInflater().inflate(R.layout.bottom_topics_view, null);
                return mButtonView;
            }

            @Override
            protected void setData() {
                if (mButtonView!=null){
                    LinearLayout bottom_topics_view_shield=    mButtonView.findViewById(R.id.bottom_topics_view_shield);
                    LinearLayout bottom_topics_view_complaint=    mButtonView.findViewById(R.id.bottom_topics_view_complaint);
                    LinearLayout bottom_topics_view_cancel=     mButtonView.findViewById(R.id.bottom_topics_view_cancel);
                /*    topics
                    bottom_topics_view_shield.setB*/
                    bottom_topics_view_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomShareMenu.dismiss();
                        }
                    });
                    bottom_topics_view_complaint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (UserId == -1) {
                                alertDialog4 = new AlertDialog.Builder(getContext())
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
                                Intent mIntent = new Intent(getContext(), MainActivity2.class);
                                startActivity(mIntent);
                                bottomShareMenu.dismiss();
                            }

                        }
                    });
                    bottom_topics_view_shield.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (UserId == -1) {
                                alertDialog4 = new AlertDialog.Builder(getContext())
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
                                     mPresenter.shield(topics.getId(),UserId,position);
                                     bottomShareMenu.dismiss();
                            }
                        }
                    });
                }


            }
        };
        bottomShareMenu.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bottomShareMenu.dismiss();
            }
        });
    }

    @Override
    public void showLoading() {
        mPromptDialog.showLoading("Loading ...");
    }


    @Override
    public void hideLoading() {
        mPromptDialog.dismiss();
        mSmartRefreshLayout.finishRefresh(true);
        mSmartRefreshLayout.finishLoadmore(true);
    }

    @OnClick({R.id.fab})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent mIntent = new Intent(getContext(), AddDetailsActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void setText(TextView viewById) {
        activity_error_text = viewById;
    }

    @Override
    public void onClick(View v) {
        sfl_lyt_state.showContent();
        handler.sendMessageDelayed(new Message(), 300);
    }

    @Override
    public void onStart() {
        super.onStart();
        List<User> users = mManagerFactory.getStudentManager(getContext()).queryAll();
        if (users != null && users.size() > 0) {
            UserId = users.get(0).getId();
            mTopicsAdapter.setUserId(users.get(0).getId());
        }
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContent() {
        return getContext();
    }

    @Override
    public void dataNull() {
        mStateController.setState(NODATA);
    }

    @Override
    public void notifyItemChanged(int position, Topics topics) {
        mTopicsAdapter.notifyItemChanged(position,topics);
    }

    @Override
    public void status() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBranchEvent(ErrorEntity message) {
        //int img=R.mipmap.icon_network_error;
        if (getUserVisibleHint()) {
            if (message.EventName == ResponseErrorListenerImpl.EVENT_KEY.Network_Unavailable) {
                mStateController.setState(NONETWORK_ERROR);
            } else {
             /*   mStateController.setState(ERROR);
                if (activity_error_text != null) {
                    activity_error_text.setText(message.messing);
                }*/
            }
        }
    }

    @Override
    public void onItemClick(@NonNull View view, int viewType, @NonNull Object data, int position) {
        // Toast.makeText(getContext(),""+position,Toast.LENGTH_LONG).show();
        Intent mIntent = new Intent(getContext(), DetailsActivity.class);
        mIntent.putExtra("Topics", mTopicsAdapter.getInfos().get(position));
        mIntent.putExtra("UserId", UserId);
        startActivity(mIntent);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNum++;
        mPresenter.initData(pageNum,UserId);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        pageNum = 1;
        mPresenter.initData(pageNum,UserId);
    }

    AlertDialog alertDialog4;

    @Override
    public void onClick(View view, Topics adEntity, int position) {
        Intent mIntent = new Intent(getContext(), DetailsActivity.class);
        mIntent.putExtra("Topics", adEntity);
        mIntent.putExtra("UserId", UserId);
        startActivity(mIntent);
    }

    @Override
    public void onDelete(View view, Topics topics, int position) {
        mPresenter.delete(topics.getId(),position);
    }

    @Override
    public void onLike(View view, Topics topics, int position) {
        if (UserId == -1) {
            alertDialog4 = new AlertDialog.Builder(getContext())
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
        }else {
            if (topics.getIslike()==1){
                mPresenter.Unlike(topics.getId(),UserId,position);
            }else {
                mPresenter.like(topics.getId(),UserId,position);
            }
        }
    }

    @Override
    public void onMore(View view, Topics topics, int position) {
        this.topics=topics;
        this.position=position;
        bottomShareMenu.show();//显示
    }
}
