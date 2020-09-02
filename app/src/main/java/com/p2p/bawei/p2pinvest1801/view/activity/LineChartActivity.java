package com.p2p.bawei.p2pinvest1801.view.activity;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import com.bw.lib_core.view.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;

import java.util.ArrayList;

public class LineChartActivity extends BaseActivity<MyHomePresenter> implements View.OnTouchListener {

    private LineChart mLineChart;

    @Override
    public int bandLayout() {
        return R.layout.activity_linechart;
    }

    @Override
    public void initView() {

        // 温度
        mLineChart = (LineChart) findViewById(R.id.wendu);
        LineData mLineData  = getLineData(7, 40);
        showChart(mLineChart, mLineData , Color.rgb(114, 188, 223));

    }


    /**
     * 显示数据
     * @param mLineChart
     * @param mLineData
     * @param rgb
     */
    private void showChart(LineChart mLineChart, LineData mLineData, int rgb) {

        //是否在折线图上添加边框
        mLineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // 数据描述
        mLineChart.setDescription("2");
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart.setNoDataTextDescription("暂无数据");

        // 是否显示表格颜色
        mLineChart.setDrawGridBackground(false);
        // 表格的的颜色，在这里是是给颜色设置一个透明度
        //lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF);

        // 设置是否可以触摸
        mLineChart.setTouchEnabled(true);
        // 是否可以拖拽
        mLineChart.setDragEnabled(true);
        // 是否可以缩放
        mLineChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(false);

        //lineChart.setBackgroundColor(Color.rgb(Integer.parseInt("7e", 16), Integer.parseInt("ce", 16), Integer.parseInt("f4", 16)));// 设置背景
        // 设置数据
        mLineChart.setData(mLineData);

        // 设置比例图标示，就是那个一组y的value的
        Legend mLegend = mLineChart.getLegend();
        //mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        // 样式
        mLegend.setForm(Legend.LegendForm.CIRCLE);
        // 字体
        mLegend.setFormSize(0f);
        // 颜色
        mLegend.setTextColor(Color.RED);
        // 字体
        //mLegend.setTypeface(mTf);

        // 设置Y轴右边不显示数字
        mLineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = mLineChart.getXAxis();
        // 设置X轴的数据显示在报表的下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setDrawAxisLine(false);
        // 设置不从X轴发出纵向直线
        xAxis.setDrawGridLines(false);
        // 立即执行的动画,x轴
        mLineChart.animateX(2500);

}

    /**
     *生成一个数据
     *
     * @param count  表示图表中有多少个坐标点
     * @param range   用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }


        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(value, i));
        }

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "" /*测试折线图*/);
        //用y轴的集合来设置参数
        // 线宽
        lineDataSet.setLineWidth(1.0f);
        // 显示的圆形大小
        lineDataSet.setCircleSize(2.5f);
        // 显示颜色
        lineDataSet.setColor(Color.GREEN);
        // 圆形的颜色
        lineDataSet.setCircleColor(Color.GREEN);
        // 高亮的线的颜色
        lineDataSet.setHighLightColor(Color.GREEN);
        // 设置圆点的颜色
        lineDataSet.setFillColor(Color.GREEN);
        lineDataSet.setDrawCircleHole(false);
        //lineDataSet.setValueTextSize(9f);
        lineDataSet.setFillAlpha(65);

        lineDataSets.add(lineDataSet);
        LineData lineData = new LineData(xValues, lineDataSet);
        return lineData;

    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(v == mLineChart && event.getAction() == MotionEvent.ACTION_DOWN){
            v.getParent().requestDisallowInterceptTouchEvent(true);
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }
}
