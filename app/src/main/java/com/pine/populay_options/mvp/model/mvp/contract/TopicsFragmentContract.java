package com.pine.populay_options.mvp.model.mvp.contract;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Observable;

public interface TopicsFragmentContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView , android.view.View.OnClickListener {


        void setText(TextView viewById);

        @Override
        void onClick(android.view.View v);

        @Override
        void showMessage(@NonNull String message);

        Context getContent();

        void dataNull();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<Request<List<Topics>>> initData(int pageNum,int pageSize);


    }
}
