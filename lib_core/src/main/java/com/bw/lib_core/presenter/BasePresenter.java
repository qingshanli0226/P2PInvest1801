package com.bw.lib_core.presenter;

import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;

public class BasePresenter<M extends IModel,V extends IView> implements IPresenter {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void onDestroy() {
        if(mModel!=null){
            mModel.onDestroy();
            mModel=null;
        }

        if(mView!=null){
            mView=null;
        }
        System.gc();
    }
}
