package com.p2p.bawei.p2pinvest1801.home.asset;

import android.view.View;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.myview.Bean;
import com.p2p.bawei.p2pinvest1801.myview.HistogramVIew;

import java.util.ArrayList;
import java.util.List;

public class AssetTwoActivity extends BaseActivity {
    private List<Bean> list;

    private HistogramVIew two_view;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

        two_view=findViewById(R.id.two_view);
        two_view.setX_title("X");
        two_view.setY_title("Y");

    }

    @Override
    public void initData() {
        list=new ArrayList<>();
        list.add(new Bean("a",100));
        list.add(new Bean("b",150));
        list.add(new Bean("c",160));
        list.add(new Bean("d",190));
        list.add(new Bean("e",120));
        list.add(new Bean("f",100));
        list.add(new Bean("g",130));
        two_view.setList(list);
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public int bandLayout() {
        return R.layout.activity_asset_two;
    }
}
