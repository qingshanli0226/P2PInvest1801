package com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;


import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


public class PieChartActivity extends AppCompatActivity {

    private PieChartView piechart;
    private PieChartData data;         //存放数据

    private boolean hasLabels = true;                   //是否有标签
    private boolean hasLabelsOutside = false;           //标签是否在扇形外面
    private boolean hasCenterCircle = false;            //是否有中心圆
    private boolean hasCenterText1 = true;             //是否有中心的文字
    private boolean hasCenterText2 = false;             //是否有中心的文字2
    private boolean isExploded = false;                  //是否是炸开的图像
    private boolean hasLabelForSelected = false;         //选中的扇形显示标签


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        piechart = (PieChartView) findViewById(R.id.piechart);
        piechart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Toast.makeText(PieChartActivity.this, "选中值" + sliceValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
        initDatas();




    }
    private void initDatas() {
        int numValues = 5; //分几部分
        //初始化数据
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue(20, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        if (isExploded) {
            data.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            data.setCenterText1("Hello!");//设置中心文字1
        }

        if (hasCenterText2) {
            data.setCenterText2("Charts (Roboto Italic)");//设置中心文字2
        }

        piechart.setPieChartData(data);
    }
}
