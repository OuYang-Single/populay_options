package com.pine.populay_options.mvp.model.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.Book;
import com.pine.populay_options.mvp.model.mvp.contract.CustomerContract;
import com.pine.populay_options.mvp.model.mvp.contract.ForexContract;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
@ActivityScope
public class ForexPresenter extends BasePresenter<ForexContract.Model, ForexContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<Book> books ;
    @Inject
    public ForexPresenter(ForexContract.Model model, ForexContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void initData() {
        books.add(new Book(R.mipmap.ic_book1,"","Currency Trading For Dummies","Written by,Kathleen Brooks,Brian Dolan","Features forex market guidelines and sample trading plans The fun and easy way to get started in currency trading Want to capitalize on the growing forex market? This nuts–and–bolts guide gives you a step–by–step action plan for understanding and trading the forex market. It offers practical guidance and savvy tips in everything from comprehending currency quotes to using leverage, trading with fundamentals, and navigating technical analysis. Identify trading opportunities Understand what drives the market Choose a trading broker Execute a successful trade Minimize risk and maximize profit Analyze currency charts"));
        books.add(new Book(R.mipmap.ic_book2,"","Day Trading and Swing Trading the Currency Market:Technical and Fundamental Strategies To profit from Market Moves","Written by,Kathy Lien","Discover a variety of technical and fundamental profit-making strategies for trading the currency market with the Second Edition of Day Trading and Swing Trading the Currency Market. In this book, Kathy Lien–Director of Currency Research for one of the most popular Forex providers in the world–describes everything from time-tested technical and fundamental strategies you can use to compete with bank traders to a host of more fundamentally-oriented strategies involving intermarket relationships, interest rate differentials, option volatility, news events, and central bank intervention."));
    }
}