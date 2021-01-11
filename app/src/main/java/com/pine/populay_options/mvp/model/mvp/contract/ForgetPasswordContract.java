package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.Request;

import io.reactivex.Observable;

public interface ForgetPasswordContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {


        void getCode();

        Activity getActivity();

        Integer getResult();

        void initResult();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<Request<Boolean>>  isUserExists(String name, String defaultRegion);


        Observable<Request<Boolean>> changePassword(String phone, String password);
    }
}
