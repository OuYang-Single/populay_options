package com.pine.populay_options.mvp.model.mvp.ui.Service;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jess.arms.utils.ArmsUtils;
import com.pine.populay_options.R;
import com.pine.populay_options.app.utils.SPManager;
import com.pine.populay_options.mvp.model.entity.PushMessage;
import com.pine.populay_options.mvp.model.entity.Request;
import com.pine.populay_options.mvp.model.mvp.ui.activity.WebViewActivity;
import com.pine.populay_options.mvp.model.wigth.chatkit.utils.AppJs;

import java.util.Map;

import timber.log.Timber;

import static android.graphics.Color.*;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String TAG= MyFirebaseMessagingService.class.getName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i("FcmReceiverService", "onMessageReceived");
        Timber.w(TAG + " openBrowser"+"   url"+remoteMessage.getData());
        Map<String, String> map=remoteMessage.getData();
        if (map!=null) {
          Request request = ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson(  ArmsUtils.obtainAppComponentFromContext(this).gson().toJson(remoteMessage.getData()),  Request.class);
            Timber.w(TAG + " openBrowser"+"    "+ArmsUtils.obtainAppComponentFromContext(this).gson().toJson(request.getData()));
            PushMessage message = ArmsUtils.obtainAppComponentFromContext(this).gson().fromJson( request.getData().toString(),  PushMessage.class);
            /*  PushMessage message=new PushMessage();
            message.setCreateTime(Long.parseLong(map.get("createTime")));
            message.setPushContent(map.get("pushContent"));
            message.setPushTopic(map.get("pushTopic"));
            message.setUrl(map.get("url"));*/

            createNotification(getBaseContext(), message);
        }

        // Check if message contains a notification payload.
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
        //    showNotification(getBaseContext(), notification);
        }
    }

    @Override
    public void onNewToken(String token) {

        Log.i(TAG, "Refreshed token: " + token);
        SPManager.getInstance().setToken(token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
       // sendRegistrationToServer(token);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
       // sendRegistrationToServer(token);
    }
    private void createNotification(Context context, PushMessage pushMessageModel) {
        String channelId = context.getString(R.string.app_name);
        String notificationTitle = pushMessageModel.getPushTopic();
        if (notificationTitle!=null) {
            notificationTitle = channelId;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setContentTitle(notificationTitle);
        builder.setContentText(pushMessageModel.getPushContent());
        builder.setAutoCancel(true);
        long createTime = pushMessageModel.getCreateTime();
        builder.setWhen(createTime);
        String brand = Build.BRAND;
        PendingIntent intent = setPendingIntent(context, pushMessageModel);
        builder.setSmallIcon(R.mipmap.ic_crown_1);
        if (!TextUtils.isEmpty(brand) && brand.equalsIgnoreCase("samsung")) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_crown_1);
            builder.setLargeIcon(bitmap);
        }
        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            int notificationId = (int) System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(channelId, notificationManager);
            }
            notificationManager.notify(notificationId, builder.build());
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel(
            String channelId,
            NotificationManager notificationManager
    ) {
        NotificationChannel notificationChannel =
                new NotificationChannel(
                        channelId,
                        channelId,
                        NotificationManager.IMPORTANCE_DEFAULT
                );
        notificationChannel.enableLights(true); //开启指示灯，如果设备有的话。
        notificationChannel.enableVibration(true); //开启震动
        notificationChannel.setLightColor(RED); // 设置指示灯颜色
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);//设置是否应在锁定屏幕上显示此频道的通知
        notificationChannel.setShowBadge(true); //设置是否显示角标
        notificationChannel.setBypassDnd(true); // 设置绕过免打扰模式
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400}); //设置震动频率
        notificationChannel.setDescription(channelId);
        notificationManager.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    private PendingIntent setPendingIntent(Context context, PushMessage data) {
        Intent intent = null;
        String url = data.getUrl();
        if (url==null) {
            PackageManager packageManager = context.getPackageManager();
            intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        } else {
            intent=new Intent(this, WebViewActivity.class);
            intent.putExtra("type",1);
            intent.putExtra("URL",url);
          /*  PackageManager packageManager = context.getPackageManager();
            intent = packageManager.getLaunchIntentForPackage(WebViewActivity.class.getPackage().getName());*/
          //  intent
        }
        return PendingIntent.getActivity(
                context,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
    }

}

