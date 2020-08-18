package com.example.baselibrary.mvp.view;


import androidx.annotation.LayoutRes;

public interface IActivity {
    void initView();

    void initData();

    void initPresenter();

    @LayoutRes
    int bandLayout();
}
