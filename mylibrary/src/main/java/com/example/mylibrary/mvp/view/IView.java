package com.example.mylibrary.mvp.view;

public interface IView {
    default void show(){};
    default void hide(){};
    void toast(String msg);
}
