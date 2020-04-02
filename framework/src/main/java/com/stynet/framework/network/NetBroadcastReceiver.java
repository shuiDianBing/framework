package com.stynet.framework.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.stynet.framework.Printf;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by shuiDianBing on 10:02.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 网络广播
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NetBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
            Printf.outInfo(TAG, "unconnect"); // unconnect network
        }else { // connect network
            Printf.outInfo(TAG, "connect network");
        }
    }
}
