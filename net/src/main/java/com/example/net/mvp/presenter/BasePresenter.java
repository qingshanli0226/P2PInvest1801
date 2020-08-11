package com.example.net.mvp.presenter;


import com.example.net.mvp.model.IModel;
import com.example.net.mvp.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter{
    protected M mModel;
    protected V mView;
    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
    @Override
    public void destroy() {
        if(mModel!=null){
            mModel.destroy();
            mModel=null;
        }
        if(mView!=null) {
            mView = null;
        }
        System.gc();
    }

}
