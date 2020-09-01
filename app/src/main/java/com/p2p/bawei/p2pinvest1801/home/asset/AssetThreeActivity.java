package com.p2p.bawei.p2pinvest1801.home.asset;

import android.view.View;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.myview.Bean;
import com.p2p.bawei.p2pinvest1801.myview.HistogramVIew;

import java.util.ArrayList;
import java.util.List;

public class AssetThreeActivity extends BaseActivity {


    private List<Bean> list;

    private HistogramVIew three_view;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

        three_view=findViewById(R.id.three_view);
        three_view.setX_title("X");
        three_view.setY_title("Y");

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
        three_view.setList(list);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_asset_three;
    }
}
