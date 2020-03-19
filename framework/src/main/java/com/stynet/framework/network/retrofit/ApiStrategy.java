package com.stynet.framework.network.retrofit;

import com.stynet.framework.Printf;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by shuiDianBing on 14:04.
 * Refer to the website << HttpLoggingInterceptor 拦截 请求参数和请求结果 : https://www.jianshu.com/p/78e9ff82863b
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 封装网络请求拦截器
 */
public class ApiStrategy {
    private static ApiStrategy apiStrategy;
    private Retrofit retrofit;
    //private Api api;

    /**
     *
     * @return
     */
    public static ApiStrategy getInstance(){
        if(null == apiStrategy)
            apiStrategy = new ApiStrategy();
        return apiStrategy;
    }
    protected ApiStrategy(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Printf.outError(ApiStrategy.class.getName(),"OkHttp====Message:"+message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder().client(new OkHttpClient.Builder().connectTimeout(0x10, TimeUnit.SECONDS).readTimeout(0x10,TimeUnit.SECONDS).
                writeTimeout(0x10,TimeUnit.SECONDS).addInterceptor(interceptor).addNetworkInterceptor(interceptor).build()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(ResponseConverterFactory/*GsonConverterFactory*/.create()).baseUrl(host()).build();//.create(Api.class);
    }

    /**
     *
     * @return
     */
    public String host(){
        return null;//debug
    }

    /**
     *
     * @return
     */
    public static String webHost(){
        //return null;//debug
        return null;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

//    private Api getApi() {
//        return api;
//    }
}
