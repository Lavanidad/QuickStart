package com.deepspring.libcommon.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：
 */
public abstract class BaseObserver<T> implements Observer<T> {

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e, RxExceptionUtils.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }
}
