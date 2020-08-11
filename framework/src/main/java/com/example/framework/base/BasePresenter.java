package com.example.framework.base;

//      该基类的作用：管理UI的回调接口
public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V isHttpView;
    @Override
    public void attachView() {
        this.isHttpView=isHttpView;
    }

    @Override
    public void detachView() {
        this.isHttpView=null;
    }
}
