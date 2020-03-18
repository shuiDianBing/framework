package com.stynet.framework.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by shuiDianBing on 12:32.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << json转换java对象生成器
 * 问题Retrofit: End of input at line 1 column 1 path $
 * android retrofit End of input at line 1 column 1 path在使用retrofit返回为null或[]或""
 * https://blog.csdn.net/it_talk/article/details/60767060
 * Retrofit 响应体无body时解析EOFException https://blog.csdn.net/u011374875/article/details/51956113
 * Rxjava、Retrofit返回json数据解析异常处理 https://www.jianshu.com/p/337c06f322c2
 */
public class ResponseConverterFactory extends Converter.Factory {
    private final Gson gson;
    public static ResponseConverterFactory create() {
        return create(new Gson());
    }
    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);
    }
    private ResponseConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //返回我们自定义的Gson响应体变换器
        return new CustomGsonResponseBodyConverter<>(gson, gson.getAdapter(TypeToken.get(type)));
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        //返回我们自定义的Gson响应体变换器
        return new CustomGsonRequestBodyConverter<>(gson, gson.getAdapter(TypeToken.get(type)));
    }

//    @Override
//    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
//        return new Converter<ResponseBody, Object>() {
//            @Override
//            public Object convert(ResponseBody body) throws IOException {
//                if (body.contentLength() == 0) return null;
//                return delegate.convert(body);
//            }
//        };
//    }
}
