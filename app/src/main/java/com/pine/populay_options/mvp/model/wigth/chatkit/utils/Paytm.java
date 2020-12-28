package com.pine.populay_options.mvp.model.wigth.chatkit.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import timber.log.Timber;

import static com.wq.photo.widget.PickConfig.ActivityRequestCode;

public class Paytm {

    private static int versionCompare(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
            return 1;
        }
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        ///set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.length && i < vals2.length && vals1[i].equalsIgnoreCase(vals2[i])) {
            i++;
        }
        //compare first non-equal ordinal number
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
       /* the strings are equal or one string is a substring of the other
        e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"*/
        return Integer.signum(vals1.length - vals2.length);
    }
    public static void goToPaytm(Activity activit,double Amount,String OrderID,String txnToken,String MID){
        if (versionCompare(  "", "8.6.0") < 0) {
            Intent paytmIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putDouble("nativeSdkForMerchantAmount", Amount);
            bundle.putString("orderid", OrderID);
            bundle.putString("txnToken", txnToken);
            bundle.putString("mid", MID);
            paytmIntent.setComponent(new ComponentName("net.one97.paytm", "net.one97.paytm.AJRJarvisSplash"));
            paytmIntent.putExtra("paymentmode", 2); // You must have to pass hard coded 2 here, Else your transaction would not proceed.
            paytmIntent.putExtra("bill", bundle);
            activit.startActivityForResult(paytmIntent, ActivityRequestCode);
        }else{
            Intent paytmIntent = new Intent();
            paytmIntent.setComponent(new ComponentName("net.one97.paytm", "net.one97.paytm.AJRRechargePaymentActivity"));
            paytmIntent.putExtra("paymentmode", 2);
            paytmIntent.putExtra("enable_paytm_invoke", true);
            paytmIntent.putExtra("paytm_invoke", true);
            paytmIntent.putExtra("price", Amount); //this is string amount
            paytmIntent.putExtra("nativeSdkEnabled", true);
            paytmIntent.putExtra("orderid", OrderID);
            paytmIntent.putExtra("txnToken", txnToken);
            paytmIntent.putExtra("mid", MID);
            activit.startActivityForResult(paytmIntent, ActivityRequestCode);
        }
    }
    public static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            Timber.d(info.toString()); // Timber 是我打印 log 用的工具，这里只是打印一下 log
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Timber.d(e.toString()); // Timber 是我打印 log 用的工具，这里只是打印一下 log
            return false;
        }
    }

}
