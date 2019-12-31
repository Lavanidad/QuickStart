package com.deepspring.libcommon.http;



import com.deepspring.libcommon.http.helper.IHttpHelper;
import com.deepspring.libcommon.http.modelBean.LoginData;

import io.reactivex.Observable;

/**
 * @author fzy
 * @date 2019/12/23.
 * description：
 */
public class DataClient implements IHttpHelper {

    private IHttpHelper mIHttpHelper;

    public DataClient(IHttpHelper mIHttpHelper) {
        this.mIHttpHelper = mIHttpHelper;
    }


    /**
     * 网络数据请求
     */

    @Override
    public Observable<LoginData> postLoginData(String phone, String pwd, String equipmentId) {
        return mIHttpHelper.postLoginData(phone, pwd, equipmentId);
    }

}
