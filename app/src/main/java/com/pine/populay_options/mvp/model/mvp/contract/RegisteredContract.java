package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;
import android.content.Context;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.app.utils.ResourcesUtils;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface RegisteredContract {
    interface View extends IView {



        Activity getActivity();

        void getCode();

        Integer getResult();

        void initResult();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<Request<String>> getRegistered(String name, String password, boolean b);
        Observable<Request<Boolean>>  isUserExists(String name, String defaultRegion);
    }
}
