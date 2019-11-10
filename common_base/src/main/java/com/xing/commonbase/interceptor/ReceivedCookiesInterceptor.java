package com.xing.commonbase.interceptor;

import android.util.Log;

import com.xing.commonbase.util.SharedPreferenceUtil;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //拦截的cookie保存在originalResponse中
        Response originalResponse = chain.proceed(chain.request());
        //打印cookie信息
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                Log.i("TAG", "拦截的cookie是：" + header);
                cookies.add(header);
            }
            SharedPreferenceUtil.write("cookieData", "cookie", cookies);
        }
        return originalResponse;
    }
}