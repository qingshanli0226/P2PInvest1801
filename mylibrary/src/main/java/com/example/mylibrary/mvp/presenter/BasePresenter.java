package com.example.mylibrary.mvp.presenter;

import com.example.mylibrary.mvp.model.IModel;
import com.example.mylibrary.mvp.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void onDes() {
        if(mModel!=null){
        mModel.onDes();
        mModel=null;
        }
        if(mView!=null){
            mView=null;
        }
        System.gc();
    }
}
