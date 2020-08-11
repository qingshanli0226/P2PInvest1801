package com.bw.lib_core.http;

public interface IObserver<T> {

    void success(T mybean);
    void fail(String msg);
}
