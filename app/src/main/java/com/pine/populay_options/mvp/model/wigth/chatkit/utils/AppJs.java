package com.pine.populay_options.mvp.model.wigth.chatkit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.app.utils.SPManager;
import com.pine.populay_options.mvp.model.entity.PaytmEntity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import io.branch.referral.util.BranchEvent;
import timber.log.Timber;

import static com.pine.populay_options.BuildConfig.APPLICATION_ID;
import static com.pine.populay_options.mvp.model.entity.BranchEvent.CALLBACKMETHOD;

public class AppJs {
    String TOG=AppJs.class.getName();
    WebView webView;
    Context mContext;
    Activity activity;
  public AppJs(Activity activity,WebView webView){

      mContext=activity;
      this.activity=activity;
      this. webView=webView;
  }
  public AppJs(){
    };
    /**
     * 获取设备id
     * 必须保证有值
     * 获取不到的时候生成一个UUID
     */
    @JavascriptInterface
    @NonNull
    public String getDeviceId() {
        //TODO
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    /**
     * 获取个推设备id
     * 传空串就行
     */
    @JavascriptInterface
    public String takePushId() {
        Timber.w(TOG + " takePushId");
        return "";
    }

    /**
     * 获取fcm 令牌
     * 看FCM推送的文档，有监听和获取令牌的方法
     * 详情见第八点
     */
    @JavascriptInterface
    public String takeFCMPushId() {
        Timber.w(TOG + " takeFCMPushId");
        //fcm生成的注册令牌
        //TODO

        return  SPManager.getInstance().getToken();
    }

    /**
     * 获取渠道
     */
    @JavascriptInterface
    public String takeChannel() {
        return "google";
    }

    /**
     * 获取ANDROID_ID
     *
     */
    @JavascriptInterface
    public String getGoogleId() {
        Timber.w(TOG + " getGoogleId");
        //TODO
        return APPLICATION_ID;
    }

    /**
     * 集成branch包的时候已经带有Google Play Service核心jar包
     * 获取gpsadid 谷歌广告id
     * AdvertisingIdClient.getAdvertisingIdInfo() 异步方法
     */
    @JavascriptInterface
    public String getGaId() {
        Timber.w(TOG + " getGaId");
        //TODO
        try {
           ;
            return  AdvertisingIdClient.getAdvertisingIdInfo(mContext).getId();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
       return null;
    }

    /**
     * H5调用原生谷歌登录
     * 后续流程看第七点
     *
     * @param data {"sign":"","host":"https://bb.skr.today"}
     */
    @JavascriptInterface
    public void openGoogle(String data) {
        //TODO
        Timber.w(TOG + " openGoogle"+"   data=="+data);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<String>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.openGoogle,data));

    }

    /**
     * 打开paytm
     * 本地有paytm打开应用/没有打开web版 paytm支付(web版)需要新开一个页面
     * @param data  {"textToken":"","orderId":"","mid":"","amount":0.0}
     */
    @JavascriptInterface
    public void openPayTm(String data) {
        //TODO

         Timber.w(TOG + " openPayTm"+"   data=="+data);
         PaytmEntity paytmEntity=  ArmsUtils.obtainAppComponentFromContext(mContext).gson().fromJson(data, PaytmEntity.class);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<PaytmEntity>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.openPayTm,paytmEntity));



    }

    /**
     * 头像获取
     *  流程:H5调用方法 - 自己打开图片选择器 - 回调返回H5
     *  base64使用格式：Base64.NO_WRAP
     *
     * @param callbackMethod 回传图片时调用H5的方法名
     */
    @JavascriptInterface
    public void takePortraitPicture(String callbackMethod) {

        Timber.w(TOG + " takePortraitPicture"+"   callbackMethod=="+callbackMethod);
        Map<String ,String >map=new HashMap<>();
        map.put(CALLBACKMETHOD,callbackMethod);
        ArmsUtils.obtainAppComponentFromContext(mContext).gson().toJson(map);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<String>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.takePortraitPicture,ArmsUtils.obtainAppComponentFromContext(mContext).gson().toJson(map)));

    }

    /**
     * 控制webview是否显示 TitleBar
     * （点击返回键webview 后退）
     *
     * @param visible
     */
    @JavascriptInterface
    public void showTitleBar(boolean visible) {
        //TODO
        Timber.w(TOG + " showTitleBar"+"   visible=="+visible);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<Boolean>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.ShowTitleBarEVent,visible));
    }

    /**
     * AppJs是否存在交互方法 告诉H5是否存在传入的对应方法
     *
     * @param callbackMethod 回调给H5时调用的JavaScript方法
     * @param name 需要查询AppJs中是否存在的方法
     */
    @JavascriptInterface
    public void isContainsName(String callbackMethod, String name) {
        Timber.w(TOG + " isContainsName"+"   callbackMethod=="+callbackMethod+"   name=="+name);
        Map<String ,String >map=new HashMap<>();
        map.put(CALLBACKMETHOD,callbackMethod);
        map.put( com.pine.populay_options.mvp.model.entity.BranchEvent.NAME,name);
        ArmsUtils.obtainAppComponentFromContext(mContext).gson().toJson(map);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<String>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.isContainsName,ArmsUtils.obtainAppComponentFromContext(mContext).gson().toJson(map)));

    }

    /**
     * 由h5控制是否禁用系统返回键
     * @param forbid     是否禁止返回键 1:禁止
     */
    @JavascriptInterface
    public void shouldForbidSysBackPress(int forbid) {
        Timber.w(TOG + " shouldForbidSysBackPress"+"   forbid=="+forbid);
        //TODO 以下仅供参考
        //WebActivity成员变量记录下是否禁止
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent<Integer>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.isContainsName,forbid));

        //mContext.setShouldForbidBackPress(forbid);
        //WebActivity 重写onBackPressed方法 变量为1时禁止返回操作
    }

    /**
     * 由h5控制返回键功能
     *
     * @param forbid     是否禁止返回键 1:禁止
     * @param methodName 反回时调用的h5方法 例如:detailBack() webview需要执行javascrept:detailBack()
     */
    @JavascriptInterface
    public void forbidBackForJS(int forbid, String methodName) {
        Timber.w(TOG + " forbidBackForJS"+"   forbid=="+forbid+"   methodName=="+methodName);
        //TODO 以下仅供参考
        //mContext.setShouldForbidBackPress(forbid);
        //同上
      //  mContext.setBackPressJSMethod(methodName);
        Map<String ,Object >map=new HashMap<>();
        map.put(CALLBACKMETHOD,methodName);
        map.put( com.pine.populay_options.mvp.model.entity.BranchEvent.FORBID,forbid);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent< Map<String ,Object >>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.forbidBackForJS,map));

        //WebActivity成员变量记录下js方法名 在禁止返回时调用js方法
    }

    /**
     * 使用手机里面的浏览器打开 url
     *
     * @param url 打开 url
     */
    @JavascriptInterface
    public void openBrowser(String url) {
        Timber.w(TOG + " openBrowser"+"   url"+url);
        //TODO 以下仅供参考
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }

    /**
     * 打开一个基本配置的webview （不修改UA、不设置AppJs、可以缓存）
     *  打开新页面
     *  加载webview的情况分类(判断依据：url、postData、html)
     *     |-------1、只有url：webView.loadUrl()
     *     |-------2、有url和postData：webView.postUrl()
     *     |-------3、有html webView.loadDataWithBaseURL()
     *
     *
     *
     * @param json 打开web传参
     * {"title":"", 标题
     *  "url":"", 加载的地址
     *  "hasTitleBar":false, 是否显示标题栏
     *  "rewriteTitle":true, 是否通过加载的Web重写标题
     *  "stateBarTextColor":"black", 状态栏字体颜色 black|white
     *  "titleTextColor":"#FFFFFF", 标题字体颜色
     *  "titleColor":"#FFFFFF", 状态栏和标题背景色
     *  "postData":"", webView post方法时会用到
     *  "html":"", 加载htmlCode（例如：<body></body>）,
     *  "webBack":true, true:web回退(点击返回键webview可以回退就回退，无法回退的时候关闭该页面)|false(点击返回键关闭该页面) 直接关闭页面
     * }
     */
    @JavascriptInterface
    public void openPureBrowser(String json) {
        //TODO
        Timber.w(TOG + " openPureBrowser"+"   json"+json);
        EventBus.getDefault().post(new  com.pine.populay_options.mvp.model.entity.BranchEvent< String>( com.pine.populay_options.mvp.model.entity.BranchEvent.EVENT_KEY.openPureBrowser,json));


    }

    /**
     * branch事件统计
     *
     * @param eventName 统计事件名称
     */
    @JavascriptInterface
    public void branchEvent(String eventName) {
      new BranchEvent(eventName)
                .logEvent(mContext);
        Timber.w(TOG + " branchEvent"+"   eventName"+eventName);

    }

    /**
     * branch事件统计
     *
     * @param eventName  统计时间名称
     * @param parameters 自定义统计参数
     */
    @JavascriptInterface
    public void branchEvent(String eventName, String parameters) {
        Timber.w(TOG + " branchEvent"+"   eventName"+eventName+"   parameters"+parameters);
       BranchEvent branchEvent = new BranchEvent(eventName);
        JSONObject obj = null;
        try {
            obj = new JSONObject(parameters);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = obj.optString(key);
            bundle.putString(key, value);
            branchEvent.addCustomDataProperty(
                   key,
                    value
            );
        }
       branchEvent
                .logEvent(mContext);
    }

    /**
     * branch事件统计
     *
     * @param eventName  统计事件名称
     * @param parameters 自定义统计参数
     * @param alias      事件别名
     */
    @JavascriptInterface
    public void branchEvent(String eventName, String parameters, String alias) {
        Timber.w(TOG + " branchEvent"+"   eventName"+eventName+"   parameters"+parameters+"   alias"+alias);
       BranchEvent branchEvent = new BranchEvent(eventName);
        JSONObject obj = null;
        try {
            obj = new JSONObject(parameters);
            Bundle bundle = new Bundle();
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = obj.optString(key);
                bundle.putString(key, value);
                branchEvent.addCustomDataProperty(
                        key,
                        value
                );
            }
            branchEvent
                    .setCustomerEventAlias(alias)
                    .logEvent(mContext);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * facebook事件统计
     *
     * @param eventName  事件名称
     * @param valueToSum 计数数值
     * @param parameters 自定义统计参数json{}需要全是String类型
     */
    @JavascriptInterface
    public void facebookEvent(String eventName, Double valueToSum, String parameters) {
        Timber.w(TOG + " facebookEvent"+"   eventName"+eventName+"   valueToSum"+valueToSum+"   parameters"+parameters);
     AppEventsLogger logger = AppEventsLogger.newLogger(mContext);
        JSONObject obj = null;
        try {
            obj = new JSONObject(parameters);
            Bundle bundle = new Bundle();
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = obj.optString(key);
                bundle.putString(key, value);
            }
            logger.logEvent(eventName, valueToSum, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * facebook事件统计
     *
     * @param eventName  事件名称
     * @param parameters 自定义统计参数json{}需要全是String类型
     */
    @JavascriptInterface
    public void facebookEvent(String eventName, String parameters) {
        Timber.w(TOG + " facebookEvent"+"   eventName"+eventName+"   parameters"+parameters);
        AppEventsLogger logger = AppEventsLogger.newLogger(mContext);
        JSONObject obj = null;
        try {
            obj = new JSONObject(parameters);
            Bundle bundle = new Bundle();
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = obj.optString(key);
                bundle.putString(key, value);
            }
            logger.logEvent(eventName, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * facebook计数统计
     *
     * @param eventName  事件名称
     * @param valueToSum 计数数值
     */
    @JavascriptInterface
    public void facebookEvent(String eventName, Double valueToSum) {
        Timber.w(TOG + " facebookEvent"+"   eventName"+eventName+"   valueToSum"+valueToSum);
       AppEventsLogger logger = AppEventsLogger.newLogger(mContext);
        logger.logEvent(eventName, valueToSum);
    }

    /**
     * facebook 计数事件统计
     *
     * @param eventName 事件名称
     */
    @JavascriptInterface
    public void facebookEvent(String eventName) {
      AppEventsLogger logger = AppEventsLogger.newLogger(mContext);
        logger.logEvent(eventName);
    }

    /**
     * firebase事件统计
     */
    @JavascriptInterface
    public void firebaseEvent(String category, String parameters) {
        Timber.w(TOG + " firebaseEvent"+"   category"+category+"   parameters"+parameters);
        JSONObject obj = null;
        try {
            obj = new JSONObject(parameters);
            Bundle bundle = new Bundle();
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = obj.optString(key);
                bundle.putString(key, value);
            }
            FirebaseAnalytics.getInstance(mContext).logEvent(category, bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setTOG(){

    }
}
