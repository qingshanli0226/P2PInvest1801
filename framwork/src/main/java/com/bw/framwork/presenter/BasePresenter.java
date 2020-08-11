package com.bw.framwork.presenter;

import com.bw.framwork.model.IModel;
import com.bw.framwork.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void ondestory() {  //防止内存泄漏
        if (mModel!=null){
            mModel.ondestory();
            mModel=null;
        }

        if (mView!=null){
            mView=null;
        }

        System.gc();
    }
}
