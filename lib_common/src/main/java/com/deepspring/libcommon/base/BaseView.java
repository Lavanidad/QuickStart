package com.deepspring.libcommon.base;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：View 的基类
 */
public interface BaseView {

    /**
     * 正常显示
     */
    void showNormal();

    /**
     * 显示错误
     */
    void showError();

    /**
     * 正在加载
     */
    void showLoading();


    /**
     * 显示错误信息
     *
     * @param errorMsg 错误信息
     */
    void showErrorMsg(String errorMsg);


    /**
     * 显示登录页面
     */
    void showLoginView();

    /**
     * 显示登出页面
     */
    void showLogoutView();

    /**
     * 页面重新加载
     */
    void reload();
}
