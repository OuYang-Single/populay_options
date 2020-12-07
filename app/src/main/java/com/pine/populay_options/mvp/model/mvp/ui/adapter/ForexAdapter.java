package com.pine.populay_options.mvp.model.mvp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.entity.PositionOrder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ForexAdapter extends DefaultAdapter<Book> {
    @Inject
    public ForexAdapter(List<Book> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<Book> getHolder(@NonNull View v, int viewType) {
        return new ContextItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.fprex_item;
    }


    public class ContextItemBaseHolder extends BaseHolder<Book> {
         @BindView(R.id.tv_fprex_item_name)
        TextView tv_fprex_item_name;
        @BindView(R.id.img_fprex_item_cover_1)
        ImageView img_fprex_item_cover_1;
        @BindView(R.id.img_fprex_item_cover_2)
        ImageView img_fprex_item_cover_2;
        @BindView(R.id.tv_fprex_item_author)
        TextView tv_fprex_item_author;
        @BindView(R.id.tv_fprex_item_author1)
        TextView tv_fprex_item_author1;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull Book data, int position) {
            tv_fprex_item_name.setText(data.getBookName());
            if (position%2==0){
                img_fprex_item_cover_1.setVisibility(View.VISIBLE);
                img_fprex_item_cover_1.setImageResource(data.getBookImg());
            }else {
                img_fprex_item_cover_2.setVisibility(View.VISIBLE);
                img_fprex_item_cover_2.setImageResource(data.getBookImg());
            }
            String[] author=  data.getAuthor().split(",");
            String author1="";
            if (author.length>0){
                tv_fprex_item_author.setText(author[0]);
                for (int i=1;i<author.length;i++){
                    author1+=  author[i]+"\n";
                }
                tv_fprex_item_author1.setText(author1);
            }


        }
    }
}