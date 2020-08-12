package com.p2p.bawei.p2pinvest1801.mvp.view;


import android.support.annotation.LayoutRes;

public interface IActivity {
    @LayoutRes
    int banLayout();

    void initView();

    void initData();
}
