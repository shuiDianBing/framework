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
public final class ApiPolicy {
    private static ApiPolicy apiPolicy;
    private Object type;
    private static String HOST;

    /**
     *
     * @return
     */
    public static ApiPolicy getInstance(Class service,String host,boolean printf){
        HOST = host;
        if(null == apiPolicy)
            apiPolicy = new ApiPolicy(service,printf);
        return apiPolicy;
    }

    private ApiPolicy(Class service,boolean printf){
        type = new Retrofit.Builder().client(initClient(printf))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory/*GsonConverterFactory*/.create())
                .baseUrl(host()).build().create(service);
    }

    /**
     * 初始OkHttpClient
     * @param printf
     * @return
     */
    private OkHttpClient initClient(boolean printf){
        OkHttpClient.Builder okHttpClientBuild = new OkHttpClient.Builder().connectTimeout(0x10, TimeUnit.SECONDS)
                .readTimeout(0x10,TimeUnit.SECONDS).writeTimeout(0x10,TimeUnit.SECONDS);

        if(printf)
            okHttpClientBuild.addInterceptor(createHttpLogInterceptor());

        interceptor(okHttpClientBuild);
        return okHttpClientBuild/*.addNetworkInterceptor()*/.build();
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
