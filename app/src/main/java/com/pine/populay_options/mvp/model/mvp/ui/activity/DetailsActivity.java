package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerDetailsComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerLogInComponent;
import com.pine.populay_options.mvp.model.entity.ImageInfo;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.DetailsContract;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.presenter.DetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.MainPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.DetailsAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;
import com.pine.populay_options.mvp.model.wigth.chatkit.messages.MessageInput;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yds.library.IMultiImageLoader;
import com.yds.library.MultiImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import m.zz.zzlibrary.widget.BottomShareMenu;
import me.leefeng.promptlibrary.PromptDialog;

import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
import static com.pine.populay_options.mvp.model.api.Api.file;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsContract.View, OnRefreshListener, MessageInput.InputListener {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.contet)
    TextView contet;
    @BindView(R.id.chart_text)
    TextView chart_text;
    @Inject
    DetailsAdapter mDetailsAdapter;
    Topics mTopics;
    @BindView(R.id.image_like)
    ImageView image_like;
    @BindView(R.id.image_delete)
    ImageView image_delete;
    @BindView(R.id.image_comment)
    ImageView image_comment;
    @BindView(R.id.img_right_toolbar)
    ImageView img_right_toolbar;
    @BindView(R.id.message_input)
    MessageInput message_input;
    Long UserId = -1L;
    AlertDialog alertDialog4;
    BottomShareMenu bottomShareMenu;
    View mButtonView;
    @Inject
    PromptDialog promptDialog;
    @BindView(R.id.SmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @Inject
    ImageLoader mImageLoader;
    @BindView(R.id.ll_empty_data)
    LinearLayout ll_empty_data;
    @BindView(R.id.image_multi)
    MultiImageView mMultiImageView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_details;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSmartRefreshLayout.setOnRefreshListener(this);
        img_right_toolbar.setImageResource(R.mipmap.ic_more);
        img_right_toolbar.setVisibility(View.VISIBLE);
        img_right_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomShareMenu.show();//显示
            }
        });

        message_input.setInputListener(this);
        mTopics = getIntent().getParcelableExtra("Topics");
        UserId = getIntent().getLongExtra("UserId", -1);
        tv_name.setText(mTopics.getTitle());
        contet.setText(mTopics.getContent());
        if (mTopics.getIslike() == 1) {
            image_like.setColorFilter(Color.parseColor("#FF1313"));
        } else {
            image_like.setColorFilter(Color.parseColor("#CDCDCD"));
        }
        if (UserId == mTopics.getUserid()) {
            image_delete.setVisibility(View.VISIBLE);
        } else {
            image_delete.setVisibility(View.GONE);
        }
        image_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog4 = new AlertDialog.Builder(image_delete.getContext())
                        .setMessage(R.string.log_outs)
                        .setPositiveButton(R.string.determine, new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.delete(mTopics.getId());
                            }
                        })

                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog4.dismiss();
                            }
                        }).create();
                alertDialog4.show();
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        String dateString = formatter.format(mTopics.getCreateTime());
        chart_text.setText(dateString);
        mDetailsAdapter.setUserId(UserId);
        mDetailsAdapter.setmImageLoader(mImageLoader);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mDetailsAdapter);
        mToolbarBack.setVisibility(View.VISIBLE);
        if (mTopics.getImageinfoList() != null && mTopics.getImageinfoList().size() > 0) {
            mMultiImageView.setVisibility(View.VISIBLE);
            List<String> list = new ArrayList<>();
            for (ImageInfo imageInfo : mTopics.getImageinfoList()) {
                list.add(APP_DOMAIN + AppDomain +file + imageInfo.getImg());
            }
            mMultiImageView.setMultiImageLoader(new GlideLoadImage());
            ;
            mMultiImageView.setImagesData(list);//设置数据
        } else {
            mMultiImageView.setVisibility(View.GONE);
        }
        mPresenter.initData(mTopics.getId());
        setTitle(R.string.details);
        bottomShareMenu = new BottomShareMenu(this) {
            @Override
            protected View onBindView() {
                mButtonView = getLayoutInflater().inflate(R.layout.bottom_topics_view, null);
                return mButtonView;
            }

            @Override
            protected void setData() {
                if (mButtonView != null) {
                    LinearLayout bottom_topics_view_shield = mButtonView.findViewById(R.id.bottom_topics_view_shield);
                    LinearLayout bottom_topics_view_complaint = mButtonView.findViewById(R.id.bottom_topics_view_complaint);
                    LinearLayout bottom_topics_view_cancel = mButtonView.findViewById(R.id.bottom_topics_view_cancel);
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
                            } else {
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
                            } else {
                                mPresenter.shield(mTopics.getId(), UserId);
                                bottomShareMenu.dismiss();
                            }
                        }
                    });
                }
            }
        };

    }


    @OnClick({R.id.image_delete, R.id.image_comment, R.id.image_like})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.image_delete:
                alertDialog4 = new AlertDialog.Builder(image_delete.getContext())
                        .setMessage(R.string.log_outs)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog4.dismiss();
                            }
                        }).create();
                alertDialog4.show();
                break;
            case R.id.image_comment:
                message_input.setFocusable(true);
                break;
            case R.id.image_like:
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
                } else {
                    if (mTopics.getIslike() == 1) {
                        mPresenter.Unlike(mTopics.getId(), UserId);
                    } else {
                        mPresenter.like(mTopics.getId(), UserId);
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
    public void SuccessUnlike() {
        mTopics.setIslike(0);
        if (mTopics.getIslike() == 1) {
            image_like.setColorFilter(Color.parseColor("#FF1313"));
        } else {
            image_like.setColorFilter(Color.parseColor("#CDCDCD"));
        }
    }

    @Override
    public void SuccessLike() {
        mTopics.setIslike(1);
        if (mTopics.getIslike() == 1) {
            image_like.setColorFilter(Color.parseColor("#FF1313"));
        } else {
            image_like.setColorFilter(Color.parseColor("#CDCDCD"));
        }
    }

    @Override
    public void showLoading() {
        ll_empty_data.setVisibility(View.GONE);
        promptDialog.showLoading("Loading ...");
    }


    @Override
    public void hideLoading() {
        promptDialog.dismiss();
        mSmartRefreshLayout.finishRefresh(true);
    }

    @Override
    public void SuccessShield() {
        mPresenter.initData(mTopics.getId());
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void killMyself() {
        mPresenter.initData(mTopics.getId());
    }

    @Override
    public void noData() {
        ll_empty_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.initData(mTopics.getId());
    }

    @Override
    public boolean onSubmit(CharSequence input) {

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
        } else {
            if (input.toString() != null && !"".equals(input.toString())) {
                mPresenter.SubmitComments(UserId, mTopics.getId(), input.toString());
            }
        }
        return true;
    }

    public class GlideLoadImage implements IMultiImageLoader {

        @Override
        public void load(Context context, Object url, ImageView imageView) {
            mImageLoader.loadImage(context, ImageConfigImpl.builder().imageView(imageView).url((String) url).build());
        }
    }
}
