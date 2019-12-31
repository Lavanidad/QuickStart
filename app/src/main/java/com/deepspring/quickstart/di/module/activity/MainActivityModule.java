package com.deepspring.quickstart.di.module.activity;

import com.deepspring.quickstart.di.scope.ActivityScope;
import com.deepspring.quickstart.module.main.MainContract;
import com.deepspring.quickstart.module.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
@Module
public abstract class MainActivityModule {
    @ActivityScope
    @Binds
    abstract MainContract.MainActivityPresenter bindPresenter(MainPresenter presenter);
}
