package com.deepspring.libcommon.base.presenter;

import com.deepspring.libcommon.base.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * @author fzy
 * @date 2019/12/24.
 * description： Presenter 基类
 */
public interface AbstractBasePresenter<T extends BaseView> {

    /**
     * 绑定View
     * @param view
     */
    void attachView(T view);

    /**
     * 解绑View
     */
    void detachView();


    /**
     * 添加 rxBing 订阅管理器
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);
}
