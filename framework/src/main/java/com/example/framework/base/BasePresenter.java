package com.example.framework.base;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V iHttpView;
    protected Disposable gDisposable;

    @Override
    public void attachView(V iHttpView) {
        this.iHttpView = iHttpView;
    }

    @Override
    public void detachView() {
        if(gDisposable != null && !gDisposable.isDisposed()){
            gDisposable.dispose();
        }
        this.iHttpView = null;
    }
}
