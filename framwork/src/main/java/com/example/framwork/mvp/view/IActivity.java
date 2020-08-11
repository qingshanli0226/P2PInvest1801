package com.example.framwork.mvp.view;

import androidx.annotation.LayoutRes;

public interface IActivity {
    void initViews();
    void initDatas();
    @LayoutRes
    int bandLayout();
}
