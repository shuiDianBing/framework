package com.stynet.frameset.network;

import com.stynet.framework.network.retrofit.ApiPolicy;

/**
 * Created by shuiDianBing on 10:01.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class AppPolicy{
    private static final String HOST_ = "http://www.weather.com.cn";
    private static final String HOST_DEBUG = "";
    private static AppPolicy appPolicy;
    private RemoteApi api;

    public static final AppPolicy getInstance(){
        if(null == appPolicy)
            appPolicy = new AppPolicy();
        return appPolicy;
    }

    private AppPolicy() {
        api = (RemoteApi)ApiPolicy.getInstance(RemoteApi.class,"host").getType();
    }

    public RemoteApi getApi() {
        return api;
    }
}
