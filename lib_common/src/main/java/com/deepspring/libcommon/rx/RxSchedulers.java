package com.deepspring.libcommon.rx;

import android.content.Context;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author fzy
 * @date 2019/12/23.
 * description：异步线程调度，防止RxJava内存泄漏
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> observableIO2Main(final Context context) {
        return upstream -> {
            Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            return composeContext(context, observable);
        };
    }


    public static <T> ObservableTransformer<T, T> observableIO2Main() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static <T> ObservableSource<T> composeContext(Context context, Observable<T> observable) {
        if (context instanceof RxActivity) {
            return (ObservableSource<T>) observable.compose(((RxActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxFragmentActivity) {
            return (ObservableSource<T>) observable.compose(((RxFragmentActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else if (context instanceof RxAppCompatActivity) {
            return (ObservableSource<T>) observable.compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY));
        } else {
            return observable;
        }
    }
}
