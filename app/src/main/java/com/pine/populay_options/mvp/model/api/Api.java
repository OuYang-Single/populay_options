package com.pine.populay_options.mvp.model.api;

import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;

import java.util.List;

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
    String APP_DOMAIN = "http://192.168.2.10:8083";
    String APP_DOMAINS = "http://api2.32255n.com";
    String URL_BOOK = "url_name:book";
    String URL_LOGIN = "url_name:login";
    String VEST_CODE = "44F0ZINK";
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @FormUrlEncoded
    @POST("/untitled_war/user/login.do")
    Observable<Request<User>> getUsers( @Field("username") String username,
                                        @Field("password") String password);


    @POST("/untitled_war/user/logout.do")
    Observable<Request<String>> getLogout();

    @FormUrlEncoded
    @POST("/untitled_war/user/password.do")
    Observable<Request<String>> password(@Field("username") String password);

    @FormUrlEncoded
    @POST("/untitled_war/user/register.do")
    Observable<Request<String>> getRegistered(  @Field("username") String username,
                                                @Field("password") String password);
    @FormUrlEncoded
    @POST("/untitled_war/Topics/topics.do")
    Observable<Request<List<Topics>>> initData(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize);
    @FormUrlEncoded
    @POST("/untitled_war/Topics/add_topics.do")
    Observable<Request<String>> addDetails(@Field("userId") int userId,@Field("content")String content);

    @GET("/ad/{File}/{FileName}")
    Observable<ResponseBody> download(@Path("File") String File, @Path("FileName") String FileName);

    @Headers(URL_BOOK)
    @GET("/admin/client/vestSign.do")
    Observable<Request<VestSignEntity>> vestSign(@Query("vestCode") String vestCode, @Query("channelCode") String channelCode, @Query("version")String version, @Query("deviceId")String deviceId, @Query("timestamp")long timestamp);

    @Headers(URL_LOGIN)
    @GET("/user/google/doLogin2.do")
    Observable<Request<Login>> doLogin2(@Header("url_names") String  host , @Query("id") String id, @Query("name") String name, @Query("email") String email, @Query("type") int type, @Query("sign") String sign);

}
