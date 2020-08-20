package com.p2p.bawei.p2pinvest1801;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.baselibrary.bean.Bean;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.baselibrary.view.HistogramVIew;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class YuanAct extends BaseActivity {
    private HistogramVIew mYuanView;

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        mYuanView = (HistogramVIew) findViewById(R.id.yuan_view);

        List<Bean> beanArrayList = new ArrayList<Bean>();
        beanArrayList.add(new Bean("200", 200));
        beanArrayList.add(new Bean("200", 200));
        beanArrayList.add(new Bean("200", 150));
        beanArrayList.add(new Bean("200", 500));
        beanArrayList.add(new Bean("200", 400));
        mYuanView.setList(beanArrayList);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.yuan_layout;
    }
}
