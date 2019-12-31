package com.deepspring.quickstart.module.main;

import android.content.Context;

import com.deepspring.libcommon.base.BaseView;
import com.deepspring.libcommon.base.presenter.AbstractBasePresenter;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public interface MainContract {

    interface MainView extends BaseView {


    }


    interface MainActivityPresenter extends AbstractBasePresenter<MainView> {


    }
}
