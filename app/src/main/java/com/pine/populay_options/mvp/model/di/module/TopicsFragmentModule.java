package com.pine.populay_options.mvp.model.di.module;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.pine.populay_options.R;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.mvp.contract.LogInContract;
import com.pine.populay_options.mvp.model.mvp.contract.PositionContract;
import com.pine.populay_options.mvp.model.mvp.contract.TopicsFragmentContract;
import com.pine.populay_options.mvp.model.mvp.contract.TradersContract;
import com.pine.populay_options.mvp.model.mvp.model.PositionModel;
import com.pine.populay_options.mvp.model.mvp.model.TopicsFragmentModel;
import com.pine.populay_options.mvp.model.mvp.ui.adapter.TopicsAdapter;

import java.util.ArrayList;
import java.util.List;

import cz.kinst.jakub.view.StatefulLayout;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.leefeng.promptlibrary.PromptDialog;

import static com.pine.populay_options.app.utils.StatusBarUtils.State.ERROR;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NODATA;
import static com.pine.populay_options.app.utils.StatusBarUtils.State.NONETWORK_ERROR;
import static cz.kinst.jakub.view.SimpleStatefulLayout.State.PROGRESS;

@Module
public abstract class TopicsFragmentModule {
    @Binds
    abstract TopicsFragmentContract.Model bindTopicsFragmentModel(TopicsFragmentModel model);
    @ActivityScope
    @Provides
    public static List<Topics> getTopicsList(){
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    public static PromptDialog getPromptDialog(TopicsFragmentContract.View view){
        return new PromptDialog(view.getActivity());
    }
    @ActivityScope
    @Provides
    public static TopicsAdapter getTopicsAdapter(List<Topics>view){
        return new  TopicsAdapter(view);
    }

    @Provides
    public static ManagerFactory getManagerFactory() {
        return ManagerFactory.getInstance();
    }

    @ActivityScope
    @Provides
    public static StatefulLayout.StateController getStateController(TopicsFragmentContract.View mView) {
        View error= LayoutInflater.from(mView.getContent()).inflate(R.layout.activity_error, null);
        View no_error=     LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_network_error, null);
        View s=    LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_empty_view, null);
        mView.setText(((TextView)error.findViewById(R.id.tv_content)));
        error.findViewById(R.id.tv_btn).setOnClickListener(mView);
        no_error.findViewById(R.id.tv_btn).setOnClickListener(mView);
        s.findViewById(R.id.tv_btn).setOnClickListener(mView);
        return  StatefulLayout.StateController.create()
                .withState(PROGRESS, LayoutInflater.from(mView.getContent()).inflate(R.layout.custom_app_loading, null))
                .withState(NONETWORK_ERROR,  no_error)
                .withState(ERROR,error)
                .withState(NODATA,s )
                .build();
    }
}
