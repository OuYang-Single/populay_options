package com.pine.populay_options.mvp.model.mvp.contract;

import android.content.Context;
import android.content.Intent;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.ExchangeChart;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;

public interface DemoTradingContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void onCharData(List<KLineDataModel> mExchangeChart,String pairs,  String urlNam);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<List<KLineDataModel>> onChartData(String count, String pairs, String urlName);

        void onStart();
    }
}
