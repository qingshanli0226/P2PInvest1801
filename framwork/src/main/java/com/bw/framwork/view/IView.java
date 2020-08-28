package com.bw.framwork.view;

public interface IView {

    void showMsg(Object msg);

    void showLoading();

    void hideLoading();

    void showError(int code,String message);
}
