package com.pine.populay_options.mvp.model.mvp.model;

import android.app.Application;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class WaitModel extends BaseModel implements WaitContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;
    @Inject
    ManagerFactory mManagerFactory;

    @Inject
    public WaitModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public boolean isUserPresence() {

        return  mManagerFactory.getStudentManager( mApplication.getApplicationContext()).queryAll().size()>0;
    }

    @Override
    public IRepositoryManager getRepositoryManager() {
        return mRepositoryManager;
    }
}