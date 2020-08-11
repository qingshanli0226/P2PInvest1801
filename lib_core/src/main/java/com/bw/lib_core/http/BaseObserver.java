package com.bw.lib_core.http;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> implements IObserver<T> {

    @Override
    public void fail(String msg) {

        Log.i("wby", "fail: "+msg);
    }

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
