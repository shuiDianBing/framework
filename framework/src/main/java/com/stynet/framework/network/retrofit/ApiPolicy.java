package com.stynet.framework.network.retrofit;

import com.stynet.framework.Printf;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by shuiDianBing on 9:55.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 内部创建生成请求接口
 */
public class ApiPolicy extends ApiStrategy{
//    private static ApiPolicy apiPolicy;
    private Object type;
    private static String HOST;

    /**
     *
     * @return
     */
//    public static ApiPolicy getInstance(Class service,String host,boolean printf){
//        HOST = host;
//        if(null == apiPolicy)
//            apiPolicy = new ApiPolicy(service,printf);
//        return apiPolicy;
//    }

    protected ApiPolicy(Class service,String host,boolean printf){
        super(printf);
        HOST = host;
        type = getRetrofit().create(service);
    }

    /**
     *
     * @return
     */
    @Override
    public String host(){
        return HOST;//debug
    }

    /**
     *
     * @return
     */
    public static String webHost(){
        //return null;//debug
        return HOST;
    }

    public Object getType() {
        return type;
    }

//    private Api getApi() {
//        return api;
//    }
}
