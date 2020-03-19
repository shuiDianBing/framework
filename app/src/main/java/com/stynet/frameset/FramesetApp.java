package com.stynet.frameset;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by shuiDianBing on 12:48.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 管理app
 */
public class FramesetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
