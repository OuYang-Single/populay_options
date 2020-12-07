package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.di.component.DaggerBookDetalisComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerForexComponent;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.mvp.contract.BookDetalisContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;
import com.pine.populay_options.mvp.model.mvp.presenter.BookDetalisPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.ForexPresenter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ForexAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class BookDetalisActivity extends BaseActivity<BookDetalisPresenter> implements BookDetalisContract.View {
    @BindView(R.id.toolbar_back)
    RelativeLayout mToolbarBack;
    @BindView(R.id.tv_book_detalis_introduction)
    TextView mTvBookDetalisIntroduction;
    @BindView(R.id.img_book_detalis_cover)
    ImageView mimgBookDetalisCover;
    @BindView(R.id.tv_book_detalis_name)
    TextView mTvBookDetalisName;
    @BindView(R.id.tv_book_detalis_author)
    TextView mTvBookDetalisAuthor;
    @BindView(R.id.tv_book_detalis_author1)
    TextView mTvBookDetalisAuthor1;
    Book mBook;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBookDetalisComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.book_detalis_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mToolbarBack.setVisibility(View.VISIBLE);
        setTitle(R.string.book_detalis);
        mBook=getIntent().getParcelableExtra("Book");
        mTvBookDetalisIntroduction.setText(mBook.getIntroduction());
        mTvBookDetalisName.setText(mBook.getBookName());
        String[] author=  mBook.getAuthor().split(",");
        String author1="";
        if (author.length>0){
            mTvBookDetalisAuthor.setText(author[0]);
            for (int i=1;i<author.length;i++){
                author1+=  author[i]+"\n";
            }
            mTvBookDetalisAuthor1.setText(author1);
        }
        mimgBookDetalisCover.setImageResource(mBook.getBookImg());
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
