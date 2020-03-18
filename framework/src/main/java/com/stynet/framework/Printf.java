package com.stynet.framework;

import android.app.Application;
import android.util.Log;

/**
 * Created by shuiDianBing on 11:20.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 日志信息输出
 */
public class Printf {

    private static boolean isOut = true;//日志控制

    /**
     * 建议在main中设置，避免重复调用
     * @param application
     * @param isOut
     */
    public Printf(Application application,boolean isOut) {
        this.isOut = isOut;
    }

    /**
     * 输出信息
     * @param tag
     * @param out
     */
    public static final void outInfo(String tag, String out){
        if(isOut)
            Log.i(tag,out);
    }

    /**
     * 输出信息
     * @param tag
     * @param out
     */
    public static final void outInfo(Class<?> tag, String out){
        if(isOut)
            Log.i(tag.getName(),out);
    }

    /**
     * 输出信息
     * @param tag
     * @param out
     */
    public static final void outInfo(Object tag, String out){
        if(isOut)
            Log.i(tag.getClass().getName(),out);
    }

    /**
     * 输出调试信息
     * @param tag
     * @param out
     */
    public static final void outDebug(String tag,String out){
        if(isOut)
            Log.d(tag,out);
    }

    /**
     * 输出调试信息
     * @param tag
     * @param out
     */
    public static final void outDebug(Class<?> tag, String out){
        if(isOut)
            Log.d(tag.getName(),out);
    }

    /**
     * 输出调试信息
     * @param tag
     * @param out
     */
    public static final void outDebug(Object tag, String out){
        if(isOut)
            Log.d(tag.getClass().getName(),out);
    }

    /**
     * 输出警告信息
     * @param tag
     * @param out
     */
    public static final void outWarnning(String tag,String out){
        if(isOut)
            Log.w(tag,out);
    }

    /**
     * 输出警告信息
     * @param tag
     * @param out
     */
    public static final void outWarnning(Class<?> tag, String out){
        if(isOut)
            Log.w(tag.getName(),out);
    }

    /**
     * 输出警告信息
     * @param tag
     * @param out
     */
    public static final void outWarnning(Object tag, String out){
        if(isOut)
            Log.w(tag.getClass().getName(),out);
    }

    /**
     * 输出报错信息
     * @param tag
     * @param out
     */
    public static final void outError(String tag,String out){
        if(isOut)
            Log.e(tag,out);
    }

    /**
     * 输出报错信息
     * @param tag
     * @param out
     */
    public static final void outError(Class<?> tag, String out){
        if(isOut)
            Log.e(tag.getName(),out);
    }

    /**
     * 输出报错信息
     * @param tag
     * @param out
     */
    public static final void outError(Object tag, String out){
        if(isOut)
            Log.e(tag.getClass().getName(),out);
    }

    /**
     * 输出断言信息
     * @param tag
     * @param out
     */
    public static final void outAssert(String tag,String out){
        if(isOut)
            Log.wtf(tag,out);
    }

    /**
     * 输出断言信息
     * @param tag
     * @param out
     */
    public static final void outAssert(Class<?> tag, String out){
        if(isOut)
            Log.wtf(tag.getName(),out);
    }

    /**
     * 输出断言信息
     * @param tag
     * @param out
     */
    public static final void outAssert(Object tag, String out){
        if(isOut)
            Log.wtf(tag.getClass().getName(),out);
    }

    /**
     * 输出冗长信息
     * @param tag
     * @param out
     */
    public static final void outVerbose(String tag,String out){
        if(isOut)
            Log.v(tag,out);
    }

    /**
     * 输出冗长信息
     * @param tag
     * @param out
     */
    public static final void outVerbose(Class<?> tag, String out){
        if(isOut)
            Log.v(tag.getName(),out);
    }

    /**
     * 输出冗长信息
     * @param tag
     * @param out
     */
    public static final void outVerbose(Object tag, String out){
        if(isOut)
            Log.v(tag.getClass().getName(),out);
    }
}
