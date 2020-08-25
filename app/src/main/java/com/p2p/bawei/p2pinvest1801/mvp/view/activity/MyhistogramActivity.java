package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

public class MyhistogramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);
        BarChart chart = (BarChart) findViewById(R.id.barchart);
        BarCharts mBarChart3 = new BarCharts(chart);
        BarData data = new BarData(mBarChart3.getXAxisValues(), mBarChart3.getDataSet());
        // 设置数据
        chart.setData(data);
    }

}

class BarCharts {
    public BarCharts(BarChart chart) {
        // 数据描述
        chart.setDescription("");
        // 动画
        chart.animateY(1000);
        // 设置是否可以触摸
        chart.setTouchEnabled(true);
        // 是否可以拖拽
        chart.setDragEnabled(false);
        // 是否可以缩放
        chart.setScaleEnabled(false);
        // 集双指缩放
        chart.setPinchZoom(false);
        // 隐藏右边的坐标轴
        chart.getAxisRight().setEnabled(false);
        // 隐藏左边的左边轴
        chart.getAxisLeft().setEnabled(false);

        Legend mLegend = chart.getLegend(); // 设置比例图标示
        // 设置窗体样式
        mLegend.setForm(Legend.LegendForm.SQUARE);
        // 字体
        mLegend.setFormSize(4f);
        // 字体颜色
        mLegend.setTextColor(Color.parseColor("#7e7e7e"));


        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        chart.invalidate();
    }
    public ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        ArrayList<BarEntry> valueSet3 = new ArrayList<BarEntry>();
        BarEntry v3e1 = new BarEntry(20.000f, 0); // Jan
        valueSet3.add(v3e1);
        BarEntry v3e2 = new BarEntry(60.000f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(90.000f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(150.000f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(120.000f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(40.000f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "CSDN");
        barDataSet1.setColor(Color.parseColor("#F26077"));
        barDataSet1.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "github");
        barDataSet2.setColor(Color.parseColor("#00C0BF"));
        barDataSet2.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "stackoverflow");
        barDataSet3.setColor(Color.parseColor("#DEAD26"));
        barDataSet3.setBarShadowColor(Color.parseColor("#01000000"));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        return dataSets;
    }
    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("JAVAWEB");
        xAxis.add("ANDROID");
        xAxis.add("IOS");
        xAxis.add("前端");
        xAxis.add("PHP");
        xAxis.add("C++");
        return xAxis;
    }
}
