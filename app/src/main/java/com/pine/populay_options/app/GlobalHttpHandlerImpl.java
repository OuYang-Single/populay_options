package com.pine.populay_options.app;

import android.content.Context;

import android.util.Log;
import com.google.gson.Gson;
import com.jess.arms.http.GlobalHttpHandler;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
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
     if (response.code()!=200){//HttpException\
         Gson gson=new Gson();
         com.pine.populay_options.mvp.model.entity.Request mRequest= null;
          mRequest = gson.fromJson(httpResult, com.pine.populay_options.mvp.model.entity.Request.class);
         response=response.newBuilder().message(mRequest.getMsg()).build();

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
        }
   /*     return chain.request().newBuilder().header("token", tokenId)
                .build();*/
        return request;
    }
}
