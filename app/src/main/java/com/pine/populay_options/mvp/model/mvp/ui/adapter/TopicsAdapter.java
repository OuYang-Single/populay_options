package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.AdEntity;
import com.pine.populay_options.mvp.model.entity.Billboard;
import com.pine.populay_options.mvp.model.entity.ImageInfo;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.ui.activity.DetailsActivity;
import com.yds.library.IMultiImageLoader;
import com.yds.library.MultiImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
import static com.pine.populay_options.mvp.model.api.Api.file;


public class TopicsAdapter extends DefaultAdapter<Topics> {
    AlertDialog alertDialog4;
    ImageLoader mImageLoader;
    private onClickTopicsItmeAdListener onClickTopicsItmeAdListener;

    public void setImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader=mImageLoader;
    }

    public interface onClickTopicsItmeAdListener {
        void onClick(View view, Topics adEntity, int position);

        void onDelete(View view, Topics topics, int position);

        void onLike(View view, Topics topics, int position);

        void onMore(View view, Topics topics, int position);
    }

    public void setOnClickListener(onClickTopicsItmeAdListener onClickTopicsItmeAdListener) {
        this.onClickTopicsItmeAdListener = onClickTopicsItmeAdListener;
    }

    public void setList(List<Topics> mDataList) {
        mInfos.clear();
        mInfos.addAll(mDataList);
        notifyDataSetChanged();
    }

    public TopicsAdapter(List<Topics> infos) {
        super(infos);
    }

    Long UserId = -1L;

    @NonNull
    @Override
    public BaseHolder<Topics> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.topics_item;
    }

    public void setUserId(Long id) {
        UserId = id;
    }


    public class ContextItemBaseHolder extends BaseHolder<Topics> {
        @BindView(R.id.image_multi)
        MultiImageView mMultiImageView;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.chart_texts)
        TextView chart_text;
        @BindView(R.id.chart_text)
        TextView chart_texts;
        @BindView(R.id.image_like)
        ImageView image_like;
        @BindView(R.id.image_delete)
        ImageView image_delete;
        @BindView(R.id.image_comment)
        ImageView image_comment;
        @BindView(R.id.more_text)
        ImageView more_text;
        @BindView(R.id.neo)
        CircularImageView mCircularImageView;

        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void setData(@NonNull Topics data, int position) {
            tv_name.setText(data.getTitle());
            mImageLoader.loadImage(mCircularImageView.getContext(), ImageConfigImpl.builder().imageView(mCircularImageView).url(APP_DOMAIN+AppDomain+file+data.getImage()).placeholder(R.mipmap.img_avatar).errorPic(R.mipmap.img_avatar).build());
            chart_text.setText(data.getContent());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String dateString = formatter.format(data.getCreateTime());
            chart_texts.setText(dateString);
            image_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickTopicsItmeAdListener != null) {
                        onClickTopicsItmeAdListener.onLike(v, data, position);
                    }
                }
            });
            if (data.getIslike() == 1) {
                image_like.setColorFilter(Color.parseColor("#FF1313"));
            } else {
                image_like.setColorFilter(Color.parseColor("#CDCDCD"));
            }
            if (UserId == data.getUserid()) {
                image_delete.setVisibility(View.VISIBLE);
            } else {
                image_delete.setVisibility(View.GONE);
            }
            image_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickTopicsItmeAdListener != null) {
                        onClickTopicsItmeAdListener.onClick(v, data, position);
                    }

                }
            });
            more_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickTopicsItmeAdListener != null) {
                        onClickTopicsItmeAdListener.onMore(v, data, position);
                    }

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
                                    if (onClickTopicsItmeAdListener != null) {
                                        onClickTopicsItmeAdListener.onDelete(v, data, position);
                                    }
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
            if (data.getImageinfoList() != null && data.getImageinfoList().size() > 0) {
                mMultiImageView.setVisibility(View.VISIBLE);
                List<String> list = new ArrayList<>();
                for (ImageInfo imageInfo : data.getImageinfoList()) {
                    list.add(APP_DOMAIN+AppDomain+file + imageInfo.getImg());
                }
                mMultiImageView.setMultiImageLoader(new GlideLoadImage());;
                mMultiImageView.setImagesData(list);//设置数据
            } else {
                mMultiImageView.setVisibility(View.GONE);
            }
        }
    }
     public class     GlideLoadImage implements IMultiImageLoader {

         @Override
         public void load(Context context, Object url, ImageView imageView) {
             mImageLoader.loadImage(context,ImageConfigImpl.builder().imageView(imageView).url((String) url).build());
         }
     }
}