package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.p2p.bawei.p2pinvest1801.mvp.model.IModel;
import com.p2p.bawei.p2pinvest1801.mvp.view.IView;

public class BasePresenter<V extends IView,M extends IModel> implements IPresenter {
    protected V mView;
    protected M mModel;

    public BasePresenter(V mView, M mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.onDestroy();
            mModel = null;
        }
        if (mView != null) {
            mView = null;
        }
        System.gc();
    }
}
