package com.pine.populay_options.mvp.model.di.module;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.R;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.mvp.contract.WaitContract;
import com.pine.populay_options.mvp.model.mvp.model.WaitModel;

import cz.kinst.jakub.view.StatefulLayout;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NODATA;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static cz.kinst.jakub.view.SimpleStatefulLayout.State.PROGRESS;


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
    @ActivityScope
    @Provides
    public static StatefulLayout.StateController getStateController(WaitContract.View mView) {
        View error= LayoutInflater.from(mView.getContent()).inflate(R.layout.activity_error, null);
        View no_error=     LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_network_error, null);
        mView.setText(((TextView)error.findViewById(R.id.tv_content)));
        error.findViewById(R.id.tv_btn).setOnClickListener(mView);
        no_error.findViewById(R.id.tv_btn).setOnClickListener(mView);
        return  StatefulLayout.StateController.create()
                .withState(PROGRESS, LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_app_loading, null))
                .withState(NONETWORK_ERROR,  no_error)
                .withState(ERROR,error)
                .withState(NODATA, LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_network_error, null))
                .build();
    }

}