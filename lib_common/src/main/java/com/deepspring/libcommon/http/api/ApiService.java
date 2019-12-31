package com.deepspring.libcommon.http.api;


import com.deepspring.libcommon.http.modelBean.LoginData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author fzy
 * @date 2019/12/17.
 * description： 后台接口
 */
public interface ApiService {

    public static final String BASE_URL = "";


    /**
     * 登录接口
     *
     * @param phone
     * @param pwd
     * @param equipmentId 设备号
     * @return
     */
    @FormUrlEncoded
    @POST("esapp/api/user/login")
    Observable<LoginData> postLoginData(@Field("phone") String phone, @Field("pwd") String pwd, @Field("equipmentId") String equipmentId);

}
