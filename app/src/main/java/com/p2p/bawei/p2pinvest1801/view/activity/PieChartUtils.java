package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼图
 */
public class PieChartUtils {

    private Context mContext;
    private AssetManager assets;
    private Typeface tf;
    private Resources rs;

    public PieChartUtils(Context context) {
        this.mContext = context;
        assets = mContext.getAssets();
        rs = mContext.getResources();
    }

    void showChart(PieChart pieChart, PieData pieData) {

        pieChart.setHoleColor(Color.TRANSPARENT);

        pieChart.setHoleRadius(45f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
//        pieChart.setHoleRadius(0); //实心圆
        pieChart.setDescription("测试饼状图");
        pieChart.setDrawHoleEnabled(true);
        // mChart.setDrawYValues(true);//设置是否显示y轴的值的数据
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度
        // mChart.setDrawXValues(true);
        pieChart.setRotationEnabled(true); // 可以手动旋转
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutQuad); //设置动画
        pieChart.setUsePercentValues(true);  //显示成百分比
        pieChart.setCenterText("Android\n厂商占比 ");  //饼状图中间的文字
        pieChart.setCenterTextSize(20);//饼状图中间的文字大小

        pieChart.setHoleColor(Color.WHITE);//设置中心圆颜色
        //设置数据
        pieChart.setData(pieData);
        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);
        pieChart.animateXY(1000, 1000);  //设置动画
    }

    /**
     * @param count 分成几部分
     * @param range 百分比
     */
    PieData getPieData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>(); // xVals用来表示每个饼块上的内容
        ArrayList<Entry> yValues = new ArrayList<Entry>(); // yVals用来表示封装每个饼块的实际数据
        List<Integer> colors = new ArrayList<Integer>();// 每一块饼上的颜色
        xValues.add(0, "三星");
        xValues.add(1, "华为");
        xValues.add(2, "oppo");
        xValues.add(3, "vivo");

        yValues.add(new Entry(5, 0));
        yValues.add(new Entry(10, 1));
        yValues.add(new Entry(15, 2));
        yValues.add(new Entry(20, 3));


        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(205, 198, 184));
//        colors.add(0xffff00ff);
//        colors.add(0xff0000ff);
//        colors.add(0xffffff00);
        // y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, " ");// 显示在比例图上
        pieDataSet.setSliceSpace(0f); // 设置个饼状图之间的距离
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = rs.getDisplayMetrics();
        float px = 2 * (metrics.densityDpi / 180f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度
        PieData pieData = new PieData(xValues, pieDataSet);
        return pieData;
    }

}
