package com.deepspring.libcommon.http.helper;



import com.deepspring.libcommon.http.api.ApiService;
import com.deepspring.libcommon.http.modelBean.LoginData;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author fzy
 * @date 2019/12/23.
 * description：网络请求帮助实现类 获取数据 Observable
 */
public class IHttpHelperImpl implements IHttpHelper{

    public NetworHelper mNetworkUtils;

    private ApiService getApiService() {
        return mNetworkUtils.getApiService();
    }

    @Inject
    public IHttpHelperImpl(NetworHelper networkUtils) {
        this.mNetworkUtils = networkUtils;
    }

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @param equipmentId 设备号
     * @return
     */
    @Override
    public Observable<LoginData> postLoginData(String phone, String pwd, String equipmentId) {
        return getApiService().postLoginData(phone, pwd, equipmentId);
    }

}
