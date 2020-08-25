package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.utils.PieChartUtils;

import java.util.ArrayList;
import java.util.List;

public class MychartActivity extends AppCompatActivity {
    private PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mychart);
        mChart = (PieChart) findViewById(R.id.mPieChart);
        PieChartUtils chartUtil = new PieChartUtils(this);
        PieData mPieData =chartUtil.getPieData(3, 100);
        chartUtil.showChart(mChart, mPieData);

    }

}
