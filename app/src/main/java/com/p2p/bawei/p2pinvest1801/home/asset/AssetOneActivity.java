package com.p2p.bawei.p2pinvest1801.home.asset;

import android.os.Bundle;
import android.view.View;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.myview.Bean;
import com.p2p.bawei.p2pinvest1801.myview.HistogramVIew;

import java.util.ArrayList;
import java.util.List;

public class AssetOneActivity extends BaseActivity {
    private List<Bean> list;

    private HistogramVIew one_view;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

        one_view=findViewById(R.id.one_view);
        one_view.setX_title("X");
        one_view.setY_title("Y");

    }

    @Override
    public void initData() {
        list=new ArrayList<>();
        list.add(new Bean("Jan",87));
        list.add(new Bean("Feb",71));
        list.add(new Bean("Mar",67));
        list.add(new Bean("Apr",44));
        list.add(new Bean("May",93));
        list.add(new Bean("Jun",74));
        list.add(new Bean("July",100));
        one_view.setList(list);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_asset_one;
    }


}
