package com.stynet.framework.network;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresPermission;

import com.stynet.framework.Printf;
import com.stynet.framework.util.ShellUtils;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by shuiDianBing on 22:23.
 * Refer to the website << nullptr
 * 【Android】获取当前网络状态总结 https://www.jianshu.com/p/83c28dcc6f75
 * Android获取网络状态 https://www.jianshu.com/p/10ed9ae02775
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 网络类型链接状态
 */
public final class NetworkUtils {

    public enum NetworkType {
        NETWORK_WIFI,
        NETWORK_6G,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return null == manager ? null : manager.getActiveNetworkInfo();
    }

    /**
     * 打开无线设置
     * @param context
     */
    public static void openWirelessSettings(Context context){
        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * Return whether using mobile data.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean isMobileData(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isAvailable() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * Return whether using 4G.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean is4G(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null
                && info.isAvailable()
                && info.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
    }


    /**
     * Return whether mobile data is enabled.
     *
     * @return {@code true}: enabled<br>{@code false}: disabled
     */
    public static boolean getMobileDataEnabled(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm == null) return false;
            @SuppressLint("PrivateApi")
            Method getMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("getDataEnabled");
            if (null != getMobileDataEnabledMethod) {
                return (boolean) getMobileDataEnabledMethod.invoke(tm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Set mobile data enabled.
     * <p>Must hold {@code android:sharedUserId="android.uid.system"},
     * {@code <uses-permission android:name="android.permission.MODIFY_PHONE_STATE" />}</p>
     *
     * @param enabled True to enabled, false otherwise.
     */
    @RequiresPermission(Manifest.permission.MODIFY_PHONE_STATE)
    public static void setMobileDataEnabled(Context context,final boolean enabled) {
        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            if (tm == null) return;

            Method setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
            if (null != setMobileDataEnabledMethod) {
                setMobileDataEnabledMethod.invoke(tm, enabled);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return whether wifi is enabled.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />}</p>
     *
     * @return {@code true}: enabled<br>{@code false}: disabled
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static boolean getWifiEnabled(Context context) {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        return manager != null && manager.isWifiEnabled();
    }

    /**
     * Set wifi enabled.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />}</p>
     *
     * @param enabled True to enabled, false otherwise.
     */
    @SuppressLint("MissingPermission")
    public static void setWifiEnabled(Context context,final boolean enabled) {
        @SuppressLint("WifiManagerLeak")
        WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if (manager == null) return;
        if (enabled) {
            if (!manager.isWifiEnabled()) {
                manager.setWifiEnabled(true);
            }
        } else {
            if (manager.isWifiEnabled()) {
                manager.setWifiEnabled(false);
            }
        }
    }

    /**
     * Return whether wifi is connected. WIFI网络是否可用
     * <p>Must hold
     *  @param context
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return {@code true}: connected<br>{@code false}: disconnected
     */
    @SuppressLint("MissingPermission")
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null
                && cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Return whether wifi is available.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
     * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return {@code true}: available<br>{@code false}: unavailable
     */
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET})
    public static boolean isWifiAvailable(Context context) {
        return getWifiEnabled(context) && isAvailableByPing(null);
    }

    /**
     * Return the name of network operate.网络运营名称
     *
     * @return the name of network operate
     */
    public static String getNetworkOperatorName(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getNetworkOperatorName() : null;
    }

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
    public static final NetworkType getAPNType(Context context) {
        NetworkType netType = NetworkType.NETWORK_NO;//默认无网络
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (null != networkInfo && networkInfo.isAvailable()) {
            switch (networkInfo.getType()){
                case ConnectivityManager.TYPE_WIFI://wifi
                    netType = NetworkType.NETWORK_WIFI;
                case ConnectivityManager.TYPE_MOBILE://移动网络
                    //TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    //if(!telephony.isNetworkRoaming())
                        //netType = getNetworkClass(telephony.getNetworkType());
                        netType = getNetworkClass(networkInfo.getSubtype());
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
    public static NetworkType getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN://2G网络 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
                return NetworkType.NETWORK_2G;//TelephonyManager.NETWORK_CLASS_2_G;
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
                return NetworkType.NETWORK_3G;//TelephonyManager.NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
            case TelephonyManager.NETWORK_TYPE_IWLAN:
            case 19://TelephonyManager.NETWORK_TYPE_LTE_CA://4G网络
                return NetworkType.NETWORK_4G;//TelephonyManager.NETWORK_CLASS_4_G;
            case TelephonyManager.NETWORK_TYPE_NR://5G网络
                return NetworkType.NETWORK_5G;
            default://未知网络
                return NetworkType.NETWORK_UNKNOWN;//TelephonyManager.NETWORK_CLASS_UNKNOWN;
        }
    }

    /**
     * Return the domain address.
     * <p>Must hold {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @param domain The name of domain.
     * @return the domain address
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public static String getDomainAddress(final String domain) {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(domain);
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return the ip address.
     * <p>Must hold {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @param useIPv4 True to use ipv4, false otherwise.
     * @return the ip address
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public static String getIPAddress(final boolean useIPv4) {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp()) continue;
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean isIPv4 = hostAddress.indexOf(':') < 0;
                        if (useIPv4) {
                            if (isIPv4) return hostAddress;
                        } else {
                            if (!isIPv4) {
                                int index = hostAddress.indexOf('%');
                                return index < 0
                                        ? hostAddress.toUpperCase()
                                        : hostAddress.substring(0, index).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return whether network is available using ping.
     * <p>Must hold {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @param ip The ip address.
     * @return {@code true}: yes<br>{@code false}: no
     */
    @RequiresPermission(Manifest.permission.INTERNET)
    public static boolean isAvailableByPing(String ip) {
        if (ip == null || ip.length() <= 0) {
            ip = "223.5.5.5";// default ping ip
        }
        ShellUtils.CommandResult result = ShellUtils.execCmd(String.format("ping -c 1 %s", ip), false);
        boolean ret = result.result == 0;
        if (result.errorMsg != null) {
            Printf.outDebug("NetworkUtils", "isAvailableByPing() called" + result.errorMsg);
        }
        if (result.successMsg != null) {
            Printf.outDebug("NetworkUtils", "isAvailableByPing() called" + result.successMsg);
        }
        return ret;
    }
}
