package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.MainContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.ViewPagerContentAdapter;

import java.util.List;

import javax.inject.Inject;
@ActivityScope
public class TopicsFragmentModel  extends BaseModel implements TopicsFragmentContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public TopicsFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}