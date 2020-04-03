package com.stynet.framework.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shuiDianBing on 16:07.
 * Refer to the website << nullptr
 * https://github.com/zangp/StatusBarUtils
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class ToobarUtils {
    /**
     * google 谷歌原生方式改变状态栏文字颜色
     * 一旦用谷歌原生设置状态栏文字颜色的方法进行设置的话，
     * 因为一直会携带SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN这个flag，那么默认界面会变成全屏模式，
     * 需要在根布局中设置FitsSystemWindows属性为true，所以我在基类的 process方法中加入如下的代码。
     * 或在xml文件跟布局添加 android:fitsSystemWindows="true"
     *
     * @param activity
     * @param dark
     */
    protected static void setAndroidNativeLightStatusBar(Activity activity,boolean dark){
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    /**
     * 设置状态栏透明，启用全屏模式
     * 沉浸状态栏
     * @param color 状态栏颜色
     */
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static final void statusBarColor(Activity activity,int color){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity,android.R.color.transparent));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 5.0以上系统状态栏透明
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if(Color.TRANSPARENT > color && Color.WHITE < color)//判断颜色是否越界
                color = Color.TRANSPARENT;//超出颜色范围设置为透明
            window.setStatusBarColor(color);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void process(Activity activity,Bundle savedInstanceState) {
        // 华为,OPPO机型在StatusBarUtil.setLightStatusBar后布局被顶到状态栏上去了
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View content = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            if (null != content) {
                content.setFitsSystemWindows(true);
            }
        }
    }

    /**
     *  修改状态栏文字颜色，这里小米，魅族区别对待。
     */
    public static void setLightStatusBar(final Activity activity, final boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(PhoneManufacturer.isMiUIV6OrAbove())
                MIUISetStatusBarLightMode(activity, dark);
            //else if(PhoneManufacturer.isMiUIV7OrAbove())
            //    ;
            else if(PhoneManufacturer.isFlymeV4OrAbove())
                setFlymeLightStatusBar(activity, dark);
            else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                setAndroidNativeLightStatusBar(activity, dark);
        }
    }

    /**
     * 小米系统下状态栏文字颜色的修改
     * @param activity
     * @param dark
     * @return
     */
    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && PhoneManufacturer.isMiUIV7OrAbove()) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (dark) {
                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    } else {
                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    }
                }
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 魅族系统状态栏文字颜色
     * @param activity
     * @param dark
     * @return
     */
    private static boolean setFlymeLightStatusBar(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }
}
