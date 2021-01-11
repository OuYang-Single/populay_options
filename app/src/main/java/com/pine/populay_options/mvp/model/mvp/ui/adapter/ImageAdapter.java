package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.pine.populay_options.R;
import com.wq.photo.widget.PickConfig;
import com.yalantis.ucrop.UCrop;

import java.util.List;

import static com.jess.arms.integration.AppManager.getAppManager;
import static com.pine.populay_options.mvp.model.entity.BranchEvent.CALLBACKMETHOD;
import static com.wq.photo.widget.PickConfig.PICK_REQUEST_CODES;

public class ImageAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private ImageLoader mImageLoader;

    public ImageAdapter(List<String> list, Context context, ImageLoader mImageLoader) {
        this.context = context;
        this.list = list;
        this.mImageLoader = mImageLoader;
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_item_ad_layout_imgs, null, false);;
            holder.img = (ImageView) convertView.findViewById(R.id.home_item_vp_ad_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       if (position>=list.size()){
           holder.img.setImageResource(R.mipmap.img_adds);
          // holder.img.setColorFilter(Color.parseColor("#CDCDCD"));
       }else {
           mImageLoader.loadImage(holder.img.getContext(), ImageConfigImpl.builder().imageView(holder.img ).url(list.get(position)).build());
       }

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>=list.size()){
                //图片剪裁的一些设置
                UCrop.Options options = new UCrop.Options();
                //图片生成格式
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                //图片压缩比
                options.setCompressionQuality(2);
                new PickConfig.Builder(getAppManager().getCurrentActivity())
                        .maxPickSize(9)//最多选择几张
                        .isneedcamera(false)//是否需要第一项是相机
                        .spanCount(4)//一行显示几张照片
                        .actionBarcolor(Color.parseColor("#ffffff"))//设置toolbar的颜色
                        .statusBarcolor(Color.parseColor("#ffffff")) //设置状态栏的颜色(5.0以上)
                        .isneedcrop(true)//受否需要剪裁
                        .setUropOptions(options) //设置剪裁参数
                        .isSqureCrop(true) //是否是正方形格式剪裁
                        .requestCode(PICK_REQUEST_CODES)
                        .pickMode(PickConfig.MODE_MULTIP_PICK)//单选还是多选
                        .callbackMethod("")
                        .build();
            }
            }
        });

        return convertView;

    }
    public final class ViewHolder{
            public ImageView img;

        }
}
