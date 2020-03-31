package com.stynet.framework.network.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shuiDianBing on 9:55.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 内部创建生成请求接口
 */
public class ApiPolicy extends ApiStrategy{
//    private static ApiPolicy apiPolicy;
    private Object type;
    private Map headMap,bodyMap;//公共头部,公共体

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
        super(host,printf);
        type = getRetrofit().create(service);
    }

    public Object getType() {
        return type;
    }

    /**
     * 设置共用参数
     * @param headMap
     * @param bodyMap
     */
    public void setCommonParam(Map headMap,Map bodyMap){
        this.headMap = headMap;
        this.bodyMap = bodyMap;
    }

    @Override
    protected void interceptor(OkHttpClient.Builder builder) {
        super.interceptor(builder);
        if(null != headMap && null != bodyMap)
            builder.addInterceptor(createInterceptor());
    }

    private Interceptor createInterceptor(){
        return new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request.Builder requestBuilder = originRequest.newBuilder();
                addHeads(requestBuilder);
                String method = originRequest.method();
                switch (method){
                    case "GET":
                        addGets(originRequest.url());
                        break;
                    case "POST":
                        requestBuilder.post(addPosts(originRequest.body()));
                        break;
                }
                return chain.proceed(requestBuilder.build());
            }
        };
    }

    /**
     * 添加头部
     * @param builder
     */
    private void addHeads(Request.Builder builder){
        if(null != headMap) {
            for (Object key : headMap.keySet())
                builder.addHeader(key.toString(), headMap.get(key).toString());
        }
    }

    /**
     * okHttp请求公共参数 https://www.jianshu.com/p/78bdef9fd78a
     * @param httpUrl
     */
    private void addGets(HttpUrl httpUrl){
        //请求参数集合
//        Set<String> paramNames = url.queryParameterNames();
        HttpUrl.Builder builder = httpUrl.newBuilder();
        for(Object key : bodyMap.keySet())
            builder.addQueryParameter(key.toString(),bodyMap.get(key).toString());
    }

    /**
     * 添加体
     * 记录项目中的Retrofit请求参数封装 https://www.jianshu.com/p/eaa652d61f57
     * @param body
     * @return
     */
    private FormBody addPosts(RequestBody body){
        //因为我们项目需要获取参数 进行进一步处理，需要将参数记录下来
        FormBody.Builder builder = new FormBody.Builder();
        FormBody formBody = (FormBody) body;

        int size = formBody.size();
        if (size > 0) {
            //循环将传入的 表单添加到新的表单
            for (int i = 0; i < size; i++) {
                String name = formBody.name(i);
                String value = formBody.value(i);
                for(Object key : bodyMap.keySet()) {
                    if (name.equals(key))
                        value = bodyMap.get(key).toString();
                }
                builder.add(name,value);
            }
        } else
            for(Object key : bodyMap.keySet())
                builder.add(key.toString(), bodyMap.get(key).toString());
            return builder.build();
    }
}
