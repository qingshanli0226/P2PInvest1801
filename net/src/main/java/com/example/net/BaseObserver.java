package com.example.net;

import org.json.JSONException;

import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        if(e instanceof JSONException){
            onRequestError("101",e.getMessage());
        }else if(e instanceof HttpException){
            onRequestError("102", e.getMessage());
        }else if(e instanceof SocketException){
            onRequestError("103", e.getMessage());
        }else if(e instanceof NetException){
            NetException netException = (NetException) e;
            onRequestError("104",e.getMessage());
        }
    }

    public abstract void onRequestError(String code,String message);
    @Override
    public void onComplete() {

    }
}
