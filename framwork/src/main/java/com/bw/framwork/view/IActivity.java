package com.bw.framwork.view;

import android.support.annotation.LayoutRes;

public interface IActivity {

    void initView();
    void initData();

    @LayoutRes
    int bandLayout();


}
