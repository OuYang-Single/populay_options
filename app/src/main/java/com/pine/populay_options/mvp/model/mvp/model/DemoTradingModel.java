package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.DemoTradingContract;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;

import javax.inject.Inject;
@ActivityScope
public class DemoTradingModel extends BaseModel implements DemoTradingContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public DemoTradingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

}
