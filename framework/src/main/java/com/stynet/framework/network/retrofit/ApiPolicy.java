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
 * function << 封装网络请求拦截器 << 内部创建生成请求接口
 * {@link ApiStrategy}
 */
public class ApiPolicy {
    private static ApiPolicy apiPolicy;
    private Object type;
    private static String HOST;

    /**
     *
     * @return
     */
    public static ApiPolicy getInstance(Class service,String host){
        HOST = host;
        if(null == apiPolicy)
            apiPolicy = new ApiPolicy(service);
        return apiPolicy;
    }

    private ApiPolicy(Class service){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Printf.outError(ApiStrategy.class.getName(),"OkHttp====Message:"+message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        type = new Retrofit.Builder().client(new OkHttpClient.Builder().connectTimeout(0x10, TimeUnit.SECONDS)
                .readTimeout(0x10,TimeUnit.SECONDS).writeTimeout(0x10,TimeUnit.SECONDS)
                .addInterceptor(interceptor).addNetworkInterceptor(interceptor).build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory/*GsonConverterFactory*/.create())
                .baseUrl(host()).build().create(service);
    }

    /**
     *
     * @return
     */
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
