package com.deepspring.libcommon.http.helper;

import com.deepspring.libcommon.BuildConfig;
import com.deepspring.libcommon.common.BaseConstants;
import com.deepspring.libcommon.http.api.ApiService;
import com.deepspring.libcommon.http.cookie.CookieManager;
import com.deepspring.libcommon.http.interceptor.CacheInterceptor;
import com.deepspring.libcommon.http.interceptor.HeaderInterceptor;
import com.deepspring.libcommon.http.interceptor.LoggingInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fzy
 * @date 2019/12/17.
 * description： 网络请求工具
 */
public class NetworHelper {

    public static final String TAG = NetworHelper.class.getSimpleName();

    private Retrofit.Builder mRetrofitBuilder;

    private HeaderInterceptor headerInterceptor = new HeaderInterceptor();
    private CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private LoggingInterceptor loggingInterceptor;

    @Inject
    public NetworHelper() {
        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient());
    }

    /**
     * 获取网络请求对象
     *
     * @return
     */
    public ApiService getApiService() {
        return mRetrofitBuilder.build().create(ApiService.class);
    }


    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            loggingInterceptor = new LoggingInterceptor();
            //日志拦截器
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        File netCachePath = new File(BaseConstants.PATH_NET_CACHE);
        Cache netCache = new Cache(netCachePath, 1024 * 1024 * 50);
        //拦截器
        clientBuilder.addInterceptor(headerInterceptor);
        clientBuilder.addInterceptor(cacheInterceptor);
        clientBuilder.addNetworkInterceptor(cacheInterceptor);
        clientBuilder.cache(netCache);
        //设置超时时间
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        //cookie操作处理
        clientBuilder.cookieJar(CookieManager.getInstance());
        return clientBuilder.build();
    }

}
