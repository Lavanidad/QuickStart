package com.deepspring.libcommon.http.interceptor;


import com.deepspring.libcommon.utils.Tools;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author fzy
 * @date 2019/11/6.
 * description：cache
 */
public class CacheInterceptor implements Interceptor {

    /**
     * 缓存机制
     * 实现：在没有网络的情况下，重新打开App可以显示之前的内容。
     * 过程：判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取
     */
    public CacheInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!Tools.isNetworkConnected()) {
            CacheControl cacheControl = new CacheControl.Builder().onlyIfCached()
                    .maxStale(60 * 60 * 24 * 7, TimeUnit.SECONDS).build();
            request = request.newBuilder().cacheControl(cacheControl).build();
        }
        Response response = chain.proceed(request);
        if (Tools.isNetworkConnected()) {
            //如果有网络
            int maxAge = 0;
            // 有网络时, 不缓存, 最大保存时长为0
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            // 无网络时，设置超时为1周，在响应体中设置无效
            int maxStale = 60 * 60 * 24 * 7;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
