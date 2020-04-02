package com.stynet.framework.network;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresPermission;

/**
 * Created by shuiDianBing on 22:23.
 * Refer to the website << nullptr
 * 【Android】获取当前网络状态总结 https://www.jianshu.com/p/83c28dcc6f75
 * Android获取网络状态 https://www.jianshu.com/p/10ed9ae02775
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 网络类型链接状态
 */
public final class Network {
    /**
     * 1.判断是否有网络连接
     *
     * @param context
     * @return 有网时返回true，没网时返回false
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static final boolean isConnected(Context context){
        if(null != context){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(null != networkInfo)
                return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return 是WIFI网络返回true，不是WIFI返回false
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static final boolean isWifiConnected(Context context){
        if(null != context){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
                return mWiFiNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 判断数据流量是否可用
     * @param context
     * @return 是数据流量时返回true，不是返回false
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static final boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
     * @param context
     * @return
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static final int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 获取当前的网络状态
     * 没有网络0：WIFI网络1：3G网络2：2G网络3
     * Android-判断当前网络是否可用 https://www.cnblogs.com/sishuiliuyun/p/4037913.html
     * @param context
     * @return
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static final int getAPNType(Context context) {
        int netType = -1;//默认无网络
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (null != networkInfo) {
            int nType = networkInfo.getType();
            switch (netType){
                case ConnectivityManager.TYPE_WIFI://wifi
                    netType = 1;
                case ConnectivityManager.TYPE_MOBILE://移动网络
                    TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    if(!telephony.isNetworkRoaming())
                        netType = getNetworkClass(telephony.getNetworkType());
                    break;
            }
        }
        return netType;
    }

    /**
     * {@link TelephonyManager#getNetworkClass}
     * @param networkType
     * @return
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN://2G网络 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
                return 2;//TelephonyManager.NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA://3G网络,联通的3G为UMTS或HSDPA 电信的3G为EVDO
                return 3;//TelephonyManager.NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
            case TelephonyManager.NETWORK_TYPE_IWLAN:
            case 19://TelephonyManager.NETWORK_TYPE_LTE_CA://4G网络
                return 4;//TelephonyManager.NETWORK_CLASS_4_G;
            case TelephonyManager.NETWORK_TYPE_NR://5G网络
                return 5;
            default://未知网络
                return 0;//TelephonyManager.NETWORK_CLASS_UNKNOWN;
        }
    }
}
