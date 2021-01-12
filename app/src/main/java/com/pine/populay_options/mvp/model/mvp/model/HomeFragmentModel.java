package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.R;
import com.pine.populay_options.mvp.model.entity.AdEntity;
import com.pine.populay_options.mvp.model.entity.BandItem;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;
import com.pine.populay_options.mvp.model.mvp.contract.ForgetPasswordContract;
import com.pine.populay_options.mvp.model.mvp.contract.HomeFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeContentAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdPagerAdapter;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.HomeItemAdapter;

import java.util.List;

import javax.inject.Inject;

import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAIN;
import static com.pine.populay_options.mvp.model.api.Api.AppDomain;
import static com.pine.populay_options.mvp.model.entity.BandItem.CONTENT;
import static com.pine.populay_options.mvp.model.entity.BandItem.OPTION;

@ActivityScope
public class HomeFragmentModel extends BaseModel implements  HomeFragmentContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    List<BandItem> mBandItemList;
    @Inject
    HomeItemAdPagerAdapter mHomeItmeAdPagerAdapter;
    @Inject
    List<AdEntity> mAdEntityList;
    @Inject
    HomeContentAdapter mHomeItemOptionAdapter;
    @Inject
    HomeItemAdapter homeItemAdapter;
    @Inject
    List<ExchangEreal> exchangEreals;

    @Inject
    public HomeFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void initData() {
        BandItem<List<AdEntity>, HomeItemAdPagerAdapter> bandItem = new BandItem();
        AdEntity mAdEntity=new AdEntity();
        mAdEntity.setJumpType(R.mipmap.a123);
        mAdEntity.setId(R.mipmap.a123);
        mAdEntity.setWebsiteAddress(APP_DOMAIN+AppDomain+"/artile_details.html");
        mAdEntityList.add(mAdEntity);
        AdEntity  mAdEntity1=new AdEntity();
        mAdEntity1.setJumpType(R.mipmap.a1234);
      //  mAdEntity1.setImageAddress("https://fifin.com/weo/userimg/use/cover/20201225/1608861909iktlZa.jpeg");
        mAdEntity.setId(R.mipmap.a1234);
        mAdEntity1.setWebsiteAddress(APP_DOMAIN+AppDomain+"/leading_position.html");
        mAdEntityList.add(mAdEntity1);
        AdEntity  mAdEntity2=new AdEntity();
        mAdEntity2.setId(R.mipmap.a12345);
        mAdEntity2.setJumpType(R.mipmap.a12345);
       // mAdEntity2.setImageAddress("https://fifin.com/weo/userimg/use/cover/20201231/1609381620WQVZ4F.jpeg");
        mAdEntity2.setWebsiteAddress(APP_DOMAIN+AppDomain+"/review.html");
        //mAdEntity2.setJumpType(R.mipmap.ic_carousel);
        mAdEntityList.add(mAdEntity2);
        bandItem.setAdapter(mHomeItmeAdPagerAdapter);
        bandItem.setData(mAdEntityList);
        mBandItemList.add(bandItem);
        BandItem<List<ExchangEreal>,HomeItemAdPagerAdapter> bandItem1 = new BandItem();
        bandItem1.setmType(OPTION);
        mBandItemList.add(bandItem1);
        BandItem<List<ExchangEreal>,HomeItemAdapter> bandItem2 = new BandItem();
        bandItem2.setAdapter(homeItemAdapter);
        bandItem2.setmType(CONTENT);
        mBandItemList.add(bandItem2);
        mHomeItemOptionAdapter.notifyDataSetChanged();
    }
}