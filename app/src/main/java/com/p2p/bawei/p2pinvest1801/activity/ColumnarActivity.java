package com.p2p.bawei.p2pinvest1801.activity;

import com.example.framework.base.BaseActivity;
import com.lixs.charts.BarChart.LBarChartView;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

public class ColumnarActivity extends BaseActivity {

    private LBarChartView frameNewBase;
    //数据源
    private ArrayList<Double> datas = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();

    @Override
    protected void initData() {
        //添加数据源
        datas.add(91.0d);
        datas.add(45.0d);
        datas.add(62.0d);
        datas.add(84.0d);
        datas.add(76.0d);
        datas.add(43.0d);
        datas.add(87.0d);
        datas.add(97.0d);
        datas.add(92.0d);
        datas.add(66.0d);
        datas.add(82.0d);
        datas.add(40.0d);

        description.add("Jan");
        description.add("Jan");
        description.add("Mar");
        description.add("Mar");
        description.add("May");
        description.add("May");
        description.add("JuI");
        description.add("JuI");
        description.add("Sep");
        description.add("Sep");
        description.add("Nov");
        description.add("Nov");

        frameNewBase.setDatas(datas,description,true);


    }

    @Override
    protected void initView() {
        frameNewBase = (LBarChartView) findViewById(R.id.frameNewBase);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_column;
    }
}
