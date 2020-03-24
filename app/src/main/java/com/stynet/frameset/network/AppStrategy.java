package com.stynet.frameset.network;

import com.stynet.framework.network.retrofit.ApiStrategy;

/**
 * Created by shuiDianBing on 9:34.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class AppStrategy{
    private static final String HOST_ = "http://www.weather.com.cn";
    private static final String HOST_DEBUG = "";
    private static AppStrategy appStrategy;
    private RemoteApi api;

    public static final AppStrategy getInstance(){
        if(null == appStrategy)
            appStrategy = new AppStrategy();
        return appStrategy;
    }

    private AppStrategy() {
        api = ApiStrategy.getInstance("host").getRetrofit().create(RemoteApi.class);
    }

    public RemoteApi getApi() {
        return api;
    }
}
