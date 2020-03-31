package com.stynet.framework.network.retrofit;

import androidx.annotation.NonNull;

import com.stynet.framework.Printf;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by shuiDianBing on 14:04.
 * Refer to the website << HttpLoggingInterceptor 拦截 请求参数和请求结果 : https://www.jianshu.com/p/78e9ff82863b
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 配置retrofit
 */
public abstract class ApiStrategy {
//    private static ApiStrategy apiStrategy;
    private Retrofit retrofit;

    /**
     *
     * @return
     */
//    private static ApiStrategy getInstance(String host){
//        HOST = host;
//        if(null == apiStrategy)
//            apiStrategy = new ApiStrategy();
//        return apiStrategy;
//    }

    protected ApiStrategy(boolean printf){
        retrofit = new Retrofit.Builder().client(initClient(printf))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory/*GsonConverterFactory*/.create())
                .baseUrl(host()).build();//.create(service);
    }

    /**
     * 初始OkHttpClient
     * @param printf
     * @return
     */
    private OkHttpClient initClient(boolean printf){
        OkHttpClient.Builder okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(0xf, TimeUnit.SECONDS)
                .readTimeout(0xf,TimeUnit.SECONDS).writeTimeout(0xf,TimeUnit.SECONDS);

        if(printf)
            okHttpClientBuild.addInterceptor(createHttpLogInterceptor());

        interceptor(okHttpClientBuild);
        networkInterceptor(okHttpClientBuild);
        return okHttpClientBuild.build();
    }

    /**
     * 创建日志拦截器
     * @return
     */
    protected HttpLoggingInterceptor createHttpLogInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Printf.outError(ApiStrategy.class.getName(),"OkHttp====Message:"+message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 设置其他拦截器
     * @param builder
     */
    protected void interceptor(OkHttpClient.Builder builder){}

    /**
     * TODO 后续更新addNetworkInterceptor
     */
    protected void networkInterceptor(OkHttpClient.Builder builder){

    }

    /**
     *
     * @return
     */
    public abstract String host();

    public Retrofit getRetrofit() {
        return retrofit;
    }

//    private Api getApi() {
//        return api;
//    }
}
