package com.stynet.framework.network.retrofit;

/**
 * Created by shuiDianBing on 12:05.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << token状态
 */
public class ApiException extends  RuntimeException{
    private int errorCode;
    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        errorCode = errorCode;
    }

    /**
     * 判断是否是token失效
     *
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return errorCode == 0;
    }
}
