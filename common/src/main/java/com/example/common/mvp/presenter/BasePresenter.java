package com.example.common.mvp.presenter;

import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void Destory() {
        if (mModel!=null){
            mModel.Destory();
            mModel=null;
        }
        if (mView!=null){
            mView=null;
        }
        System.gc();
    }
}
