package com.example.framwork.mvp.view;

import android.view.View;

public interface IFragment {
    int bandLayout();

    void initView();

    void initData();

    <T extends View> T findViewById(int id);

}
