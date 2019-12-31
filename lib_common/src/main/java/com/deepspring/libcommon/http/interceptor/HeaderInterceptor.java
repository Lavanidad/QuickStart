package com.deepspring.libcommon.http.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author fzy
 * @date 2019/11/6.
 * description： sign-headers
 */
public class HeaderInterceptor implements Interceptor {

    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        String method = originalRequest.method();
        //GET请求的参数封装
        if ("get".equalsIgnoreCase(method)) {
            HttpUrl httpUrl = originalRequest.url();
            String urlParam = httpUrl.query();
            SignParams signParams = new SignParams(urlParam);
            originalRequest = originalRequest.newBuilder()
                    .headers(signParams.getRequestHeaders(originalRequest.headers()))
                    .build();

        } else if ("post".equalsIgnoreCase(method)) {
            RequestBody requestBody = originalRequest.body();
            SignParams signParams = new SignParams(requestBody);

            originalRequest = originalRequest.newBuilder()
                    .headers(signParams.getRequestHeaders(originalRequest.headers()))
                    .build();
        }
        return chain.proceed(originalRequest);
    }
}
