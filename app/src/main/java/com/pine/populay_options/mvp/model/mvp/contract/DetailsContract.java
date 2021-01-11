package com.pine.populay_options.mvp.model.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Request;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface DetailsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {


        void SuccessUnlike();

        void SuccessLike();

        void SuccessShield();

        Activity getActivity();

        void noData();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {


        Observable<Request<Boolean>> Unlike(Integer id, Long userId);

        Observable<Request<Boolean>> like(Integer id, Long userId);

        Observable<Request<Boolean>>  shield(Integer id, Long userId);

        Observable<Request<List<CommentsEntity>>> comment(Integer Integer);

        Observable<Request<Boolean>> SubmitComments(Integer id, int userId, String toString);

        Observable<Request<Boolean>> delete(Integer id);
    }
}
