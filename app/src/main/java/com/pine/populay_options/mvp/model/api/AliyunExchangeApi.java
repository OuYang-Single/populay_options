package com.pine.populay_options.mvp.model.api;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.ExchangeChart;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.mvp.model.ExchangEreal;

import java.util.List;
import java.util.Map;

import dagger.MapKey;
import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AliyunExchangeApi {
 public    static String   appcode ="911af48fc36d4600b4d0db5091582747";
    String APP_DOMAIN = "http://forex.api51.cn";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";


    @GET("/chart")
    Observable<List<KLineDataModel>> chart(@Query("count")  String count, @Query("pairs")String pairs, @Query("type") String type, @Header("Authorization") String Authorization);

    @GET("/real")
    Observable<List<ExchangEreal>> real(@Query("pairs")String pairs, @Header("Authorization") String Authorization);

}
