package com.pine.populay_options.mvp.model.api;

import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.*;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:43
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://192.168.1.101:8000";
    String APP_DOMAINS = "http://api.katlynivey.com";
    String VEST_CODE = "8YLXY2H3";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/auth/login")
    Observable<Request<User>> getUsers(@Body AuthorizationUser mAuthorizationUser);

    @GET("/ad/{File}/{FileName}")
    Observable<ResponseBody> download(@Path("File") String File, @Path("FileName") String FileName);

    @GET("/admin/client/vestSign.do")
  Observable<Request<VestSignEntity>> vestSign(@Path("vestCode") String vestCode, @Path("channelCode") String channelCode, @Part("version")String version, @Part("deviceId")String deviceId, @Part("timestamp")long timestamp);


}
