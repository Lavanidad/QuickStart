package com.deepspring.libcommon.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.deepspring.libcommon.base.BaseView;
import com.deepspring.libcommon.base.presenter.AbstractBasePresenter;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public abstract class BaseFragment<T extends AbstractBasePresenter> extends AbstractSimpleFragment implements BaseView {

    //Presenter 对象注入
    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPresenter != null){
            mPresenter = null;
        }
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

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

    @Override
    public void showErrorMsg(String errorMsg) {

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
    protected int getLayoutId() {
        return 0;
    }

}
