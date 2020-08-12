package com.example.framwork.mvp.view;

public interface IView {
    void showLoading();
    void hideLoading();
    void showMsg(String message);
    void showError(String code, String message);
}
