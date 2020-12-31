package com.pine.populay_options.mvp.model.api;

import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Login;
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
    String APP_DOMAIN = "http://10.200.162.66:8081";
    String APP_DOMAINS = "http://api2.32255n.com";
    String URL_BOOK = "url_name:book";
    String URL_LOGIN = "url_name:login";
    String VEST_CODE = "44F0ZINK";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("/mmall_war/login.do")
    Observable<Request<User>> getUsers(@Body AuthorizationUser mAuthorizationUser);

    @GET("/ad/{File}/{FileName}")
    Observable<ResponseBody> download(@Path("File") String File, @Path("FileName") String FileName);
    @Headers(URL_BOOK)
    @GET("/admin/client/vestSign.do")
  Observable<Request<VestSignEntity>> vestSign(@Query("vestCode") String vestCode, @Query("channelCode") String channelCode, @Query("version")String version, @Query("deviceId")String deviceId, @Query("timestamp")long timestamp);
    @Headers(URL_LOGIN)
    @GET("/user/google/doLogin2.do")
    Observable<Request<Login>> doLogin2(@Header("url_names") String  host , @Query("id") String id, @Query("name") String name, @Query("email") String email, @Query("type") int type, @Query("sign") String sign);

}
