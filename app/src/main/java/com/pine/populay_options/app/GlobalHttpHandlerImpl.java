package com.pine.populay_options.app;

import android.content.Context;

import android.util.Log;
import com.google.gson.Gson;
import com.jess.arms.http.GlobalHttpHandler;
import com.pine.populay_options.greendao.ManagerFactory;
import com.pine.populay_options.mvp.model.api.Api;
import com.pine.populay_options.mvp.model.entity.User;
import com.squareup.okhttp.FormEncodingBuilder;


import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.pine.populay_options.mvp.model.api.Api.APP_DOMAINS;

/**
 * ================================================
 * 展示 {@link GlobalHttpHandler} 的用法
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:43
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class GlobalHttpHandlerImpl implements GlobalHttpHandler {
    private Context context;

    public GlobalHttpHandlerImpl(Context context) {
        this.context = context;
    }

    /**
     * 这里可以先客户端一步拿到每一次 Http 请求的结果, 可以先解析成 Json, 再做一些操作, 如检测到 token 过期后
     * 重新请求 token, 并重新执行请求
     *
     * @param httpResult 服务器返回的结果 (已被框架自动转换为字符串)
     * @param chain      {@link okhttp3.Interceptor.Chain}
     * @param response   {@link Response}
     * @return
     */
    @Override
    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
        /* 这里如果发现 token 过期, 可以先请求最新的 token, 然后在拿新的 token 放入 Request 里去重新请求
        注意在这个回调之前已经调用过 proceed(), 所以这里必须自己去建立网络请求, 如使用 Okhttp 使用新的 Request 去请求
        create a new request and modify it accordingly using the new token
        Request newRequest = chain.request().newBuilder().header("token", newToken)
                             .build();
         Toas
        retry the request

        response.body().close();
        如果使用 Okhttp 将新的请求, 请求成功后, 再将 Okhttp 返回的 Response return 出去即可
        如果不需要返回新的结果, 则直接把参数 response 返回出去即可*/
     Log.w("body",response.body().toString()) ;
     if (response.code()==200){//HttpException\
         Gson gson=new Gson();
         OkHttpClient okHttpClient = new OkHttpClient();
          com.pine.populay_options.mvp.model.entity.Request mRequest= null;
          try {
              mRequest = gson.fromJson(httpResult, com.pine.populay_options.mvp.model.entity.Request.class);
              if (mRequest!=null){
                  if (mRequest.getStatus()==1){
                      List<User> users= ManagerFactory.getInstance().getStudentManager(context).queryAll();
                      if (users.size()>0){
                          MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                          builder.addFormDataPart("password", users.get(0).getPassword());//传递键值对参数
                          builder.addFormDataPart("username", users.get(0).getUsername());//传递键值对参数
                          Request request = new Request.Builder()
                                  .url(Api.APP_DOMAIN+"/untitled_war/user/login.do")
                                  .post(builder.build())
                                  .build();
                          final Call call = okHttpClient.newCall(request);
                          Response a=null;
                          Response execute=null;

                          try {
                              execute=  call.execute();
                              try {
                                  String tokem= gson.fromJson(execute.body().string(), com.pine.populay_options.mvp.model.entity.Request.class).getToken();
                                  users.get(0).setToken(tokem);
                                  ManagerFactory.getInstance().getStudentManager(context).update(users);
                                  Request newRequest =    chain.request().newBuilder() .addHeader("Set-Cookie",  tokem)
                                          .build();
                                  final Call calls=  okHttpClient.newCall(newRequest);
                                  a=   calls.execute();
                              } catch (IOException e) {
                                  e.printStackTrace();
                              }

                          } catch (IOException e) {
                              e.printStackTrace();
                          }

                      }


                  }
              }
          }catch (Exception e){

          }


        }

        return response;
    }

    /**
     * 这里可以在请求服务器之前拿到 {@link Request}, 做一些操作比如给 {@link Request} 统一添加 token 或者 header 以及参数加密等操作
     *
     * @param chain   {@link okhttp3.Interceptor.Chain}
     * @param request {@link Request}
     * @return
     */
    @Override
    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {

        List<String> headerValues = request.headers("url_name");
        if (headerValues != null && headerValues.size() > 0) {
            String headerValue = headerValues.get(0);
            if ("book".equals(headerValue)) {
                HttpUrl httpUrl=   request.url();
                HttpUrl    newBaseUrl = HttpUrl.parse(APP_DOMAINS);
                HttpUrl newBaseUrls=   httpUrl.newBuilder().host(newBaseUrl.host()).port(newBaseUrl.port()).build();
                return chain.request().newBuilder().url(newBaseUrls)
                        .build();
            }else if ("login".equals(headerValue)){
                List<String> host = request.headers("url_names");
                if (host!=null&&host.size()>0){
                    HttpUrl httpUrl=   request.url();
                    HttpUrl    newBaseUrl = HttpUrl.parse(host.get(0));
                    HttpUrl newBaseUrls=   httpUrl.newBuilder().host(newBaseUrl.host()).port(80).build();
                    return chain.request().newBuilder().url(newBaseUrls)
                            .build();
                }
            }
        }else {
         List<User> user= ManagerFactory.getInstance().getStudentManager(context).queryAll();
         if (user.size()>0){
             if (user!=null){
               /*  chain.request().newBuilder().header("Set-Cookie", user.get(0).getToken() )
                         .build();*/
             }
         }
        }

        return request;
    }
}
