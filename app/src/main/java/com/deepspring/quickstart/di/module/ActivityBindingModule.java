package com.deepspring.quickstart.di.module;


import com.deepspring.quickstart.di.module.activity.LoginActivityModule;
import com.deepspring.quickstart.di.module.activity.MainActivityModule;
import com.deepspring.quickstart.di.scope.ActivityScope;
import com.deepspring.quickstart.module.login.LoginActivity;
import com.deepspring.quickstart.module.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author fzy
 * @date 2019/12/23.
 * description： 用于生成Activity注入器的Module，使用@ContributesAndroidInjector注解并指定modules为
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivity();

}
