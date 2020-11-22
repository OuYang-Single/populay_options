package com.pine.populay_options.mvp.model.mvp.contract;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.pine.populay_options.mvp.model.entity.AliyunRequest;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;

import java.util.List;

import io.reactivex.Observable;

public interface DemoTradingFragmentContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void onCharData(List<KLineDataModel> mExchangeChart, String pairs, String urlNam, int type);

        void getOffer(ExchangEreal exchangEreal);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

        Observable<AliyunRequest<List<KLineDataModel>>> onChartData(String period, String pidx, String psize, String symbol, String withlast);

        void onStart();

        Observable<AliyunRequest<ExchangEreal>> getOffer(String pairs, String withks, String withticks);
    }
}
