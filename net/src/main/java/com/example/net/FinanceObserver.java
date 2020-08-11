package com.example.net;

import com.example.common.FinanceConstant;

import org.json.JSONException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class FinanceObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof JSONException) {
            onRequestError(FinanceConstant.JSCON_ERROR_CODE, FinanceConstant.JSON_ERROR_MESSAGE);
        } else if (e instanceof HttpException) {
            onRequestError(FinanceConstant.HTTP_ERROR_CODE, FinanceConstant.HTTP_ERROR_MESSAGE);
        } else if (e instanceof SocketTimeoutException) {
            onRequestError(FinanceConstant.SOCKET_TIMEOUT_ERROR_CODE, FinanceConstant.SOCKET_TIMEOUT_ERROR_MESSAGE);
        } else if (e instanceof NetBusinessException) {
            NetBusinessException netBusinessException = (NetBusinessException)e;
            onRequestError(netBusinessException.getErrorCode(), netBusinessException.getErrorMessage());
        } else if (e instanceof SecurityException) {

        }
    }
    public abstract void onRequestError(String errorCode, String errorMessage);
}
