package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import io.reactivex.Observable;

public interface WaitContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView, android.view.View.OnClickListener {

        Context getContent();
       void setEventSwitch(boolean isSwitch);
       void setTimeJumpTxt(@NonNull String TimeJumpTxt);

        void statusService(Intent intent);

        void vestSign(VestSignEntity data);
        void showTitleBar(boolean visible);

        void setOpenEntity(OpenEntity openEntity);

        void doLogin2(Login data);
        void doLogin2(OpenEntity openEntity, GoogleSignInAccount account);

        void setShouldForbidBackPress(int data);

        void setBackPressJSMethod(Object o);

        @Override
        void showMessage(@NonNull String message);


        void onError();

        void setText(TextView viewById);

        @Override
        void onClick(android.view.View view);

        boolean isJump();

        void Jumps();

        Activity getActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        boolean isUserPresence();

        IRepositoryManager getRepositoryManager();

        Observable<Request<VestSignEntity>> vestSign(AppJs appJs);

        void onStart();

        Observable<Request<Login>> doLogin2(OpenEntity openEntity, GoogleSignInAccount account, int type);
    }
}
