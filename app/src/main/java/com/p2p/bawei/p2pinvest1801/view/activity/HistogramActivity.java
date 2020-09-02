package com.p2p.bawei.p2pinvest1801.view.activity;

import com.bw.lib_core.view.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;

/**
 * 柱状图
 */
public class HistogramActivity extends BaseActivity<MyHomePresenter> {

    private BarChart barChart;
    private BarCharts mBarChart3;

    @Override
    public int bandLayout() {
        return R.layout.activity_histogram;
    }

    @Override
    public void initView() {

        barChart = (BarChart) findViewById(R.id.barChart);
        mBarChart3 = new BarCharts(barChart);
        BarData data = new BarData(mBarChart3.getXAxisValues(),mBarChart3.getDataSet());
        // 设置数据
        barChart.setData(data);

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }
}
