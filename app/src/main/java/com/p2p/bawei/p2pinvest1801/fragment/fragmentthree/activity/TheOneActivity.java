package com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.activity;

import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.fragment.fragmentthree.myview.HistogramVIew;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

public class TheOneActivity extends BaseActivity {
    private HistogramVIew hvId;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int banLayout() {
        return R.layout.activity_the_one;
    }

    @Override
    public void initView() {
        hvId = findViewById(R.id.Hv_id);
    }

    @Override
    public void initData() {

    }
}
