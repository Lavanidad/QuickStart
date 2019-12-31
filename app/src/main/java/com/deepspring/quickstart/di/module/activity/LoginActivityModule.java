package com.deepspring.quickstart.di.module.activity;


import com.deepspring.quickstart.di.scope.ActivityScope;
import com.deepspring.quickstart.module.login.LoginContract;
import com.deepspring.quickstart.module.login.LoginPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author fzy
 * @date 2019/12/23.
 * description： LoginActivity 可以提供的注入对象Module
 */
@Module
public abstract class LoginActivityModule {
    @ActivityScope
    @Binds
    abstract LoginContract.LoginActivityPresenter bindPresenter(LoginPresenter presenter);
}
