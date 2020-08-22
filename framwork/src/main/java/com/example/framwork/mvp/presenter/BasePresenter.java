package com.example.framwork.mvp.presenter;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView,M extends IModel> implements IPresenter {
    protected M mModel;
    protected V mView;
    protected Disposable gDisposable;
    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void destroy() {
        if(mModel != null){
            mModel.destroy();
            mModel = null;
        }
        if(mView != null){
            mView = null;
        }
        if (gDisposable!=null&&!gDisposable.isDisposed()) {
            gDisposable.dispose();
        }
        System.gc();
    }
}
