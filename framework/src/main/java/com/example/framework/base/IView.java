package com.example.framework.base;

public interface IView {
//  网络请求错误
    void showError(String code, String message);
//    网络加载提示
    void showLoading();
//    关闭网络加载的提示
    void hideLoading();

}
