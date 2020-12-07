package com.pine.populay_options.mvp.model.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

public interface ForexCalendarContract {
    interface View extends IView {

        void setTime(String time);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        String getTime();

        String Future(String string);

        String Previous(String text);
    }
}
