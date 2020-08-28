package com.p2p.bawei.p2pinvest1801.activity;

import com.bw.framwork.view.BaseActivity;
import com.lixs.charts.RadarChartView;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.UserManager;

import java.util.ArrayList;
import java.util.List;

public class BrokenLineOne extends BaseActivity {
    private RadarChartView radarChartView;


    @Override
    public void initView() {
        UserManager.getInstance().addActivity(this);

        radarChartView = findViewById(R.id.lineView);

    }

    @Override
    public void initData() {

        List<Float> datas = new ArrayList<>();
        List<String> description = new ArrayList<>();

        datas.add(0.5f);
        datas.add(0.3f);
        datas.add(0.3f);
        datas.add(0.8f);
        datas.add(1f);
        datas.add(0.4f);

        description.add("one");
        description.add("two");
        description.add("three");
        description.add("four");
        description.add("five");
        description.add("six");

        //点击动画开启
        radarChartView.setCanClickAnimation(true);
        radarChartView.setDatas(datas);
        radarChartView.setPolygonNumbers(6);
        radarChartView.setDescriptions(description);
    }

    @Override
    public int bandLayout() {
        return R.layout.activtiy_brokenlineone;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeActivity(this);
    }
}
