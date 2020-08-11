package com.p2p.bawei.p2pinvest1801.http;

public interface IObserver<T> {
    void success(T msg);
    void fail(String msg);
}
