package com.stynet.frameset.network;

import com.stynet.framework.BuildConfig;
import com.stynet.framework.network.retrofit.ApiPolicy;

/**
 * Created by shuiDianBing on 10:01.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class AppPolicy extends ApiPolicy{
    private static final String HOST = "http://www.weather.com.cn";
    private static final String HOST_DEBUG = "";
    private static AppPolicy appPolicy;
    private RemoteApi api;

    public static final AppPolicy getInstance(){
        if(null == appPolicy)
            appPolicy = new AppPolicy();
        return appPolicy;
    }

    private AppPolicy() {
        super(RemoteApi.class,HOST, BuildConfig.DEBUG);
        api = (RemoteApi) getType();
    }

    public RemoteApi getApi() {
        return api;
    }
}
