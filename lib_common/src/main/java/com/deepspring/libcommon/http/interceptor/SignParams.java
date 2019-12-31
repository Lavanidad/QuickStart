package com.deepspring.libcommon.http.interceptor;


import com.deepspring.libcommon.utils.Md5;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author fzy
 * @date 2019/11/6.
 * description：接口加签
 * 可于此处添加通用的请求头
 */
public class SignParams {

    public static final String TAG = SignParams.class.getSimpleName();


    private String sign;


    public SignParams() {

    }


    /**
     * GET 请求参数的接口
     */
    public SignParams(String urlParam) {
        this();
        if (urlParam == null || urlParam.length() == 0) {
            urlParam = "";
        }
        this.sign = sign(urlParam);
    }

    /**
     * POST 请求参数的接口
     *
     * @param requestBody
     */
    public SignParams(RequestBody requestBody) {
        this();
        StringBuilder paramString = new StringBuilder();
        sign = "";
    }


    /**
     * 添加头
     *
     * @param headers
     * @return
     */
    public Headers getRequestHeaders(Headers headers) {
        Headers.Builder builder = headers.newBuilder();
        return builder.build();
    }

    private String sign(String paramString) {
        return Md5.md5(paramString).toLowerCase();
    }
}
