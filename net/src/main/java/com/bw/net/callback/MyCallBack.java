package com.bw.net.callback;

import io.reactivex.observers.DisposableObserver;

public abstract class MyCallBack<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
