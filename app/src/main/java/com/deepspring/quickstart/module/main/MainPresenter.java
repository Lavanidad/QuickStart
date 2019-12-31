package com.deepspring.quickstart.module.main;

import android.annotation.SuppressLint;
import android.content.Context;


import com.deepspring.libcommon.base.presenter.RxBasePresenter;
import com.deepspring.libcommon.http.DataClient;
import com.deepspring.libcommon.rx.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public class MainPresenter extends RxBasePresenter<MainContract.MainView> implements MainContract.MainActivityPresenter {

    public static final String TAG = MainPresenter.class.getSimpleName();

    private DataClient mDataClient;

    @Inject
    public MainPresenter(DataClient dataClient) {
        super(dataClient);
        this.mDataClient = dataClient;
    }

    @Override
    public void attachView(MainContract.MainView view) {
        super.attachView(view);
    }


}
