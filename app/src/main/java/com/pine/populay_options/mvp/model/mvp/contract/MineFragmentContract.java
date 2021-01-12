package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface MineFragmentContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void LogOut();

        Activity getActivity();

        void modifyAvatar(User data);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<Request<String>> logOut();

        Observable<Request<User>> modifyAvatar(String img, long userId);

    }
}
