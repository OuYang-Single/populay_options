package com.pine.populay_options.mvp.model.mvp.contract;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.OpenEntity;
import com.pine.populay_options.mvp.model.entity.Request;

import io.reactivex.Observable;

public interface WebViewContract {
    interface View extends IView {


        void setOpenEntity(OpenEntity openEntity);

        void doLogin2(OpenEntity openEntity, GoogleSignInAccount account);

        void doLogin2(Login data);

        void setShouldForbidBackPress(int data);

        void setBackPressJSMethod(Object o);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {


        Observable<Request<Login>> doLogin2(OpenEntity openEntity, GoogleSignInAccount account,int type);
    }
}
