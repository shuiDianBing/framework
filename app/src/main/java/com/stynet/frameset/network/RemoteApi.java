package com.stynet.frameset.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shuiDianBing on 9:35.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public interface RemoteApi {
    @POST("/data/sk/101190408.html")@FormUrlEncoded
    Observable<Response<Response<Void>>> signin(@Field("parameter")String parameter);
}
