package com.example.framework.base;

public interface IPresenter<V extends IView> {

    void attachView(V iHttpView);

    void detachView();
}
