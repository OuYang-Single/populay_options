package com.pine.populay_options.mvp.model.api;

import com.github.mikephil.charting.stockChart.model.KLineDataModel;
import com.pine.populay_options.mvp.model.entity.AliyunRequest;
import com.pine.populay_options.mvp.model.entity.ExchangEreal;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AliyunExchangeApi {
 public    static String   appcode ="911af48fc36d4600b4d0db5091582747";
    String APP_DOMAIN = "http://alirm-gbfsb.konpn.com";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";



    @GET("/query/comkm")
    Observable<AliyunRequest<List<KLineDataModel>>> chart(@Query("period")  String period, @Query("pidx")String pidx, @Query("psize") String psize,@Query("symbol") String symbol,@Query("withlast") String withlast, @Header("Authorization") String Authorization);

    @GET("/query/com")
    Observable<AliyunRequest<ExchangEreal>> real(@Query("symbol")String pairs, @Query("withks")String withks, @Query("withticks")String withticks, @Header("Authorization") String Authorization);
    @GET("/query/comrms")
    Observable<AliyunRequest<List<ExchangEreal>>> reals(@Query("symbols")String symbols, @Header("Authorization") String Authorization);

}
