package com.deepspring.libcommon.http.helper;

import com.deepspring.libcommon.http.modelBean.LoginData;

import io.reactivex.Observable;

/**
 * @author fzy
 * @date 2019/12/23.
 * description：
 */
public interface IHttpHelper {


    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @param equipmentId 设备号
     */
    Observable<LoginData> postLoginData(String phone, String pwd, String equipmentId);
}
