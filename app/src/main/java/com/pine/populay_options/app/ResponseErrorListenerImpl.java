package com.pine.populay_options.app;

import android.content.Context;
import android.net.ParseException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.jess.arms.utils.ArmsUtils;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import org.json.JSONException;
import retrofit2.HttpException;
import retrofit2.Response;
import timber.log.Timber;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * ================================================
 * 展示 {@link ResponseErrorListener} 的用法
 * <p>
 * Created by MVPArmsTemplate on 09/06/2019 20:43
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ResponseErrorListenerImpl implements ResponseErrorListener {


    @Override
    public void handleResponseError(Context context, Throwable t) {
        Timber.tag("Catch-Error").w(t.getMessage());

        Throwable throwable= t.getCause().getCause();

        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理
        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理
        String msg = "未知错误";
        if (throwable instanceof UnknownHostException) {
            msg = "网络不可用";
        } else if (throwable instanceof SocketTimeoutException) {
            msg = "请求网络超时";
        } else if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            msg = convertStatusCode(httpException);
        } else if (throwable instanceof JsonParseException || throwable instanceof ParseException || throwable instanceof JSONException || throwable instanceof JsonIOException) {
            msg = "数据解析错误";
        }/*retrofit2.adapter.rxjava2.HttpException: HTTP 400*/
        ArmsUtils.snackbarText(msg);
    }

    private String convertStatusCode(HttpException httpException) {
        String msg="未知错误";
        Response response = httpException.response();
     //   response.errorBody
        msg= response.message();
      //  msg=    mRequest.getMessage();

      /*  if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        }else if (httpException.code() == 400) {
            msg = "错误请求";
        }  else {
            Response response = httpException.response();
            response.message();
        }*/
        return msg;
    }
}
