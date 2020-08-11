package com.example.lib_core.mvp.view;

import android.support.annotation.LayoutRes;

public interface IActivity {

    void initView();
    void initData();
    void initInJect();
    @LayoutRes
    int BandLayout();

}
