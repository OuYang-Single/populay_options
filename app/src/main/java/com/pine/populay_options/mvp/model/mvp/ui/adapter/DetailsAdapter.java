package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.yds.library.MultiImageView;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DetailsAdapter extends DefaultAdapter<CommentsEntity> {
    Long userId;
    ImageLoader mImageLoader;
    public DetailsAdapter(List<CommentsEntity> infos) {
        super(infos);
    }

    public void setmImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }

    @NonNull
    @Override
    public BaseHolder<CommentsEntity> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.details_item;
    }

    public void setUserId(Long userId) {
        this.userId=userId;
    }


    public class ContextItemBaseHolder extends BaseHolder<CommentsEntity> {

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.CircularImageView)
        CircularImageView imageView;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull CommentsEntity data, int position) {
            tv_name.setText(data.getTitle());
            content.setText(data.getComment());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String dateString = formatter.format(data.getCreateTime());
            time.setText(dateString);
            mImageLoader.loadImage(imageView.getContext(), ImageConfigImpl.builder().imageView(imageView).url(data.getImage()).placeholder(R.mipmap.img_avatar).errorPic(R.mipmap.img_avatar).build());
        }
    }
}