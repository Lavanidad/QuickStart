package com.deepspring.libcommon.base.presenter;

import android.util.Log;


import com.deepspring.libcommon.base.BaseView;
import com.deepspring.libcommon.http.DataClient;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：基于Rx的Presenter封装, 控制事件订阅的生命周期
 */
public class RxBasePresenter<T extends BaseView> implements AbstractBasePresenter<T> {

    public static final String TAG = RxBasePresenter.class.getSimpleName();

    protected T mView;
    /**
     * 一个disposable的容器，可以容纳多个disposable 防止订阅之后没有取消订阅的内存泄漏
     */
    private CompositeDisposable compositeDisposable;
    private DataClient mDataClient;

    public RxBasePresenter(DataClient dataClient) {
        this.mDataClient = dataClient;
    }

    // 将订阅时间 event 加入到 disposable的容器中
    protected void addEventSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
        if (mView != null) {
            Log.e(TAG, "mView 不为空" + mView.getClass());
        }
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }


    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addEventSubscribe(disposable);
    }

}
