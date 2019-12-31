package com.deepspring.quickstart.module.login;

import android.content.Context;
import android.util.Log;


import com.deepspring.libcommon.base.presenter.RxBasePresenter;
import com.deepspring.libcommon.common.BaseConstants;
import com.deepspring.libcommon.http.DataClient;
import com.deepspring.libcommon.http.modelBean.LoginData;
import com.deepspring.libcommon.rx.ProgressObserver;
import com.deepspring.libcommon.rx.RxSchedulers;
import com.deepspring.libcommon.utils.SPManager;
import com.deepspring.quickstart.R;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public class LoginPresenter extends RxBasePresenter<LoginContract.LoginView> implements LoginContract.LoginActivityPresenter {

    public static final String TAG = LoginPresenter.class.getSimpleName();

    private DataClient mDataClient;

    @Inject
    public LoginPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(LoginContract.LoginView view) {
        super.attachView(view);
    }

    @Override
    public void getPostLogin(Context context, String username, String password, String equipmentId) {
        Observable<LoginData> responseBodyObservable = mDataClient.postLoginData(username, password, equipmentId);
        responseBodyObservable.compose(RxSchedulers.observableIO2Main(context))
                .subscribe(new ProgressObserver<LoginData>(context, "登录中……") {
                    @Override
                    public void onSuccess(LoginData result) {
                        mView.showLoginSuccess();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        mView.showLoginFail(errorMsg);
                    }
                });
    }
}
