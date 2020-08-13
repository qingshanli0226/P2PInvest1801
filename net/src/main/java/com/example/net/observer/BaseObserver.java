package com.example.net.observer;

import android.util.Log;


import com.bumptech.glide.load.HttpException;
import com.example.common.NetCommon;
import com.example.net.http.NetBusinessException;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<T> implements IObserver<T> {

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
