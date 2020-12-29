package com.pine.populay_options.mvp.model.di.module;

import android.os.Handler;
import android.os.Message;

import com.pine.populay_options.R;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.model.WaitModel;
import com.jess.arms.di.scope.ActivityScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;


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
@Module
public abstract class WaitModule {
   public static final int TIMEJUMPTXT=1;
    @Binds
    abstract WaitContract.Model bindMainModel(WaitModel model);

    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }
    @ActivityScope
    @Provides
    public static  Handler getHandler(WaitContract.View mView) {
        return new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case TIMEJUMPTXT:
                        String TimeJumpTxt;
                        int arg1=msg.arg1;
                        TimeJumpTxt=mView.getContent().getString(R.string.wait_jump_over);
                        if (arg1==0){
                            mView.setEventSwitch(true);
                        }else {
                            TimeJumpTxt=TimeJumpTxt+"("+arg1+")";
                        }
                        if (mView!=null){
                            mView.setTimeJumpTxt(TimeJumpTxt+"");
                        }
                        break;
                }
            }
        };
    }
/*    @ActivityScope
    @Provides
    public static  StateLayoutManager getStateLayoutManager(WaitContract.View mView) {

        return   StateLayoutManager.newBuilder(mView.getContent()).contentView(R.layout.activity_wait)
                .emptyDataView(R.layout.custom_empty_view)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .netWorkErrorView(R.layout.activity_networkerror).onRetryListener(mView)
                .onNetworkListener(mView).build();
    }*/

}