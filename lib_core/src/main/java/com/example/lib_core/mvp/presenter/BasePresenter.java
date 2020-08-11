package com.example.lib_core.mvp.presenter;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void onDestroy() {
        if (mModel != null){
            mModel.onDestroy();
            mModel = null;
        }
        if (mView != null){
            mView = null;
        }
    }
}
