package com.pine.populay_options.mvp.model.api;

import com.pine.populay_options.mvp.model.entity.AuthorizationUser;
import com.pine.populay_options.mvp.model.entity.CommentsEntity;
import com.pine.populay_options.mvp.model.entity.Login;
import com.pine.populay_options.mvp.model.entity.PageInfo;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.entity.Topics;
import com.pine.populay_options.mvp.model.entity.User;
import com.pine.populay_options.mvp.model.entity.VestSignEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
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
    String APP_DOMAIN = "http://www.bitexnode.com";
    String APP_DOMAINS = "https://fcopdddd.oss-ap-south-1.aliyuncs.com";
    String URL_BOOK = "url_name:book";
    String URL_LOGIN = "url_name:login";
    String VEST_CODE = "44F0ZINK";
    String AppDomain = "";
    String file = "/upload/";
    String files = "https://fcopdddd.oss-ap-south-1.aliyuncs.com";

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @FormUrlEncoded
    @POST(AppDomain + "/user/login.do")
    Observable<Request<User>> getUsers(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST(AppDomain + "/user/login1.do")
    Observable<Request<User>> codeLogin(@Field("username") String username);

    @POST(AppDomain + "/user/logout.do")
    Observable<Request<String>> getLogout();

    @FormUrlEncoded
    @POST(AppDomain + "/user/password.do")
    Observable<Request<String>> password(@Field("username") String password);

    @FormUrlEncoded
    @POST(AppDomain + "/user/register.do")
    Observable<Request<String>> getRegistered(@Field("username") String username,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST(AppDomain + "/user/password.do")
    Observable<Request<Boolean>> changePassword(@Field("username") String username, @Field("password") String password);


    @Multipart
    @POST(AppDomain + "/user/modify_avatar.do")
    Observable<Request<User>> modifyAvatar(@Query("userId") int userId,@PartMap Map<String, RequestBody>maps);



    @FormUrlEncoded
    @POST(AppDomain + "/Topics/delete_topics.do")
    Observable<Request<Boolean>> delete_topics(@Field("topicsId") int topicsId );


    @FormUrlEncoded
    @POST(AppDomain + "/Topics/delete_comments.do")
    Observable<Request<Boolean>> delete_comments(@Field("commentId") int topicsId );

    @FormUrlEncoded
    @POST(AppDomain + "/Topics/topics.do")
    Observable<Request<PageInfo<Topics>>> initData(@Field("pageNum") int pageNum,
                                                   @Field("pageSize") int pageSize,
                                                   @Field("userId") long userId);

    @FormUrlEncoded
    @POST(AppDomain + "/Topics/Unlike.do")
    Observable<Request<Boolean>> Unlike(@Field("topicsId") Integer id, @Field("userId")  Long userId);

    @FormUrlEncoded
    @POST(AppDomain + "/Topics/like.do")
    Observable<Request<Boolean>> like( @Field("topicsId") Integer id,  @Field("userId") Long userId);

    @FormUrlEncoded
    @POST(AppDomain + "/Topics/shield.do")
    Observable<Request<Boolean>> shield( @Field("topicsId") Integer id,  @Field("userId") Long userId);

    @FormUrlEncoded
    @POST(AppDomain + "/Topics/comment.do")
    Observable<Request<List<CommentsEntity>>>  comment( @Field("topicsId") Integer id);

    @Multipart
    @POST(AppDomain + "/Topics/add_topics.do")
    Observable<Request<String>> addDetails(@Query("userId") int userId, @Query("content") String content,@PartMap Map<String, RequestBody>maps);


    @POST(AppDomain + "/Topics/add_topicss.do")
    Observable<Request<String>>  addDetailss(@Query("userId") int userId, @Query("content") String content);


    @FormUrlEncoded
    @POST(AppDomain + "/Topics/addComments.do")
    Observable<Request<Boolean>> SubmitComments( @Field("userId") int userId,@Field("topicsId") Integer id, @Field("comment") String content);
    @FormUrlEncoded
    @POST(AppDomain + "/user/is_user_exists.do")
    Observable<Request<Boolean>> isUserExists(@Field("username") String username, @Field("defaultRegion") String defaultRegion);


    @GET("/ad/{File}/{FileName}")
    Observable<ResponseBody> download(@Path("File") String File, @Path("FileName") String FileName);
   // @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @Headers({"Content-Type: application/json", "Accept: application/json","url_name:book"})
    @GET("/canshu")
    Observable<Request<VestSignEntity>> vestSign();

    @Headers(URL_LOGIN)
    @GET("/user/google/doLogin2.do")
    Observable<Request<Login>> doLogin2(@Header("url_names") String host, @Query("id") String id, @Query("name") String name, @Query("email") String email, @Query("type") int type, @Query("sign") String sign);



}
