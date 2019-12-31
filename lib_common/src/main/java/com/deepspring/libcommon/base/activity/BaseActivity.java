package com.deepspring.libcommon.base.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.deepspring.libcommon.base.BaseView;
import com.deepspring.libcommon.base.presenter.AbstractBasePresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import es.dmoral.toasty.Toasty;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：MVP BaseActivity 基类
 */
public abstract class BaseActivity<T extends AbstractBasePresenter> extends AbstractSimpleActivity implements BaseView, HasFragmentInjector, HasSupportFragmentInjector {

    //Presenter 对象注入 (注意不能使用 private )
    @Inject
    protected T mPresenter;
    /**
     * 手动实现DaggerAppCompatActivity功能
     */
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //必须在super.onCreate之前调用AndroidInjection.inject
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        hideNavigationButton();
    }

    /**
     * 隐藏 navigation 按钮
     */
    private void hideNavigationButton() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        window.setAttributes(params);
    }

    @Override
    protected void initView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }


    @SuppressLint("CheckResult")
    @Override
    public void showErrorMsg(String errorMsg) {
        Toasty.error(this, errorMsg);
    }


    @Override
    public void showLoginView() {

    }

    @Override
    public void showLogoutView() {

    }


    @Override
    public void reload() {

    }


    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
