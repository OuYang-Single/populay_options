package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Billboard;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.yds.library.MultiImageView;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TopicsAdapter  extends DefaultAdapter<Topics> {
    @Inject
    public TopicsAdapter(List<Topics> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Topics> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.topics_item;
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
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Topics data, int position) {
            tv_name.setText(data.getTitle());
            chart_text.setText(data.getContent());
            Calendar c = Calendar.getInstance();
            long time=  c.getTime().getTime()-data.getCreateTime();
            long ss=(time)/(1000); //共计秒数
            int MM = (int)ss/60;   //共计分钟数
            int hh=(int)ss/3600;  //共计小时数
            int dd=(int)hh/24;   //共计天数
            if (dd==0){
                if (hh==0){
                    if (MM==0){
                        chart_texts.setText( ss+" second ago");
                    }else {
                        chart_texts.setText( MM+" minute ago");
                    }
                }else {
                    chart_texts.setText( hh+" hour ago");
                }
            }else {
                chart_texts.setText( dd+" days ago");
            }
            if (data.getImage()!=null){
               // mMultiImageView.setImagesData(data.getImage());//设置数据
            }


        }
    }
}