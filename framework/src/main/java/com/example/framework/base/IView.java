package com.example.framework.base;

public interface IView {

    void showError(String code, String message);

    void showLoading();

    void hideLoading();
}
