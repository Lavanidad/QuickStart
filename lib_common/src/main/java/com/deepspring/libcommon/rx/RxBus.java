package com.deepspring.libcommon.rx;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author fzy
 * @date 2019/12/24.
 * description：背压 事件分发
 */
public class RxBus {

    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        //toSerialized() 方法保证线程安全
        mBus = PublishProcessor.create().toSerialized();
    }

    private static class  Holder {
        private static final RxBus RX_BUS = new RxBus();
    }

    public static RxBus getDefault() {
        return Holder.RX_BUS;
    }

    // 提供了一个新的事件
    public void post(Object obj) {
        mBus.onNext(obj);
    }

    /**
     * 根据传递的 tClass 类型返回特定类型(tClass)的 被观察者
     *
     * @param tClass 自己定义的订阅消息数据类
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }

    //是否订阅
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }
}
