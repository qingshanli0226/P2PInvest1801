package com.p2p.bawei.p2pinvest1801.http;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> implements IObserver<T> {

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        fail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
