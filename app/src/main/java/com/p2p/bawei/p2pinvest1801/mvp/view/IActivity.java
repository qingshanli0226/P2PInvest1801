package com.p2p.bawei.p2pinvest1801.mvp.view;


import android.support.annotation.LayoutRes;

public interface IActivity {
    void initData();

    void initView();

    @LayoutRes
    int banLayout();
}
