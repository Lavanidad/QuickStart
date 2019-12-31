package com.deepspring.quickstart.module.login;

import android.content.Context;

import com.deepspring.libcommon.base.BaseView;
import com.deepspring.libcommon.base.presenter.AbstractBasePresenter;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：登录契约
 */
public interface LoginContract {

    interface LoginView extends BaseView {
        void showLoginSuccess();
        void showLoginFail(String errorMsg);
    }

    interface LoginActivityPresenter extends AbstractBasePresenter<LoginView> {
        void getPostLogin(Context context, String username, String password, String equipmentId);
    }
}
