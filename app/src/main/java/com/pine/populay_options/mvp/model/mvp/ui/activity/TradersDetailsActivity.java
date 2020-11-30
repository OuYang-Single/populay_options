package com.pine.populay_options.mvp.model.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import com.pine.populay_options.R;
import com.pine.populay_options.app.BaseAseActivitys;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersComponent;
import com.pine.populay_options.mvp.model.di.component.DaggerTradersDetailsComponent;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersDetailsContract;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersDetailsPresenter;
import com.pine.populay_options.mvp.model.mvp.presenter.TradersPresenter;

import butterknife.BindView;

public class TradersDetailsActivity extends BaseAseActivitys<TradersDetailsPresenter> implements TradersDetailsContract.View{
  @BindView(R.id.toolbar_back)
  RelativeLayout mToolbarBack;
  @BindView(R.id.value)
  TextView mTextView;
  String text="If you live in the modern world, you have heard the name George Soros many times before. You must have heard some conspiracy theories regarding the man as well since, at least to some, he is the most suspicious man who has ever lived. It is not surprising for someone who is known as the man who broke the bank of England to be a little suspicious in the eyes of the unknowing. We will talk about how he came upon that name a little later. First, let us talk about who he is and where he came from and why he is one of the best Forex traders to follow and keep your eye on.\n" +
          "George Soros’s birth name was Gyorgy Schwartz. The name was changed sometimes in the 1930s by his father, as he didn’t want the Nazi Germans to single them out and hunt them down as they were doing during those dark times in Nazi Germany. The man was just a boy at the time, so the name stuck for the rest of his life. The family moved to Switzerland sometime in 1946 and then to London in 1947. George would go to the London School of Economics here, graduating in 1952 with a degree in philosophy. After this, he went on to work with the bank known as Singer & Friedlander. He would spend many years working for many different banks and financial establishments throughout those years. Many successful Forex traders’ stories are similar to this one: from nothing to everything.\n" +
          "Then, in 1969 he founded his own fund management firm, the Soros Fund Management, concentrating on managing hedge funds, which has up to date produced $40 billion in revenue since foundation. Then, in the 1970s, he found the Quantum Fund, through which he started trading on the foreign exchange market. For many years he kept selling, buying, learning, recording until the fateful day in 1992 when he broke the English bank. He realized, after many years of observation, that he could do something that would make him a whole lot of money. He took 10 billion British Pound Sterlings and short sold them. With this action he was able to make about One billion GBP Sterling in a single day, making him the man who made the most money in one day, and also bringing him fame as the best Forex trader in the world. So far.\n" +
          "Another consequence of his actions was that the GBP had to leave the European Foreign Exchange because the value of the Sterling fell far below the agreed-upon value. Which is why he is called the man who broke the bank of England. He made the bank withdraw the currency from the market. That is a pretty big thing to do.";
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTradersDetailsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_traders_details;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle("George Soros");
        mToolbarBack.setVisibility(View.VISIBLE);
        mTextView.setText(text);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
