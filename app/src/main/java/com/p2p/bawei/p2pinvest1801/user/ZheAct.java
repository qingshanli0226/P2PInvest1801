package com.p2p.bawei.p2pinvest1801.user;

import android.view.View;

import com.example.baselibrary.bean.Bean;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.baselibrary.view.HistogramVIew;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

public class ZheAct extends BaseActivity {
    @Override
    public void onClick(View view) {

    }

    @Override
    public void initView() {
        HistogramVIew mZhuView = findViewById(R.id.zhe_view);
        List<Bean> beanArrayList = new ArrayList<>();
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 200));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 200));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 200));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 200));
        beanArrayList.add(new Bean("哦九年", 150));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        beanArrayList.add(new Bean("哦九年", 500));
        beanArrayList.add(new Bean("哦九年", 400));
        mZhuView.setList(beanArrayList);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.zhe_layout;
    }
}
