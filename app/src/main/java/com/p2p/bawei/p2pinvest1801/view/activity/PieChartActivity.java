package com.p2p.bawei.p2pinvest1801.view.activity;

import com.bw.lib_core.view.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.presenter.MyAllPresenter;

public class PieChartActivity extends BaseActivity<MyAllPresenter> {

    private PieChartUtils chartUtil;
    private PieChart mChart;
    @Override
    public int bandLayout() {
        return R.layout.activity_pie_chart;
    }

    @Override
    public void initView() {

        mChart = (PieChart) findViewById(R.id.mPieChart);
        chartUtil = new PieChartUtils(this);
        PieData mPieData =chartUtil.getPieData(3, 100);
        chartUtil.showChart(mChart, mPieData);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
