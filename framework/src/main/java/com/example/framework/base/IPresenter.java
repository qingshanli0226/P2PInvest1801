package com.example.framework.base;

public interface IPresenter<V extends IView> {
//   和UI建立关联
    void attachView();
//    解除关联
    void detachView();
}
