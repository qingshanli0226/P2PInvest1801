package com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartActivity extends AppCompatActivity {
    private LineChartView mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        mChart = findViewById(R.id.chart);
        initLine();


    }
    private void initLine() {

        //创建一些“点”：
        ArrayList<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(1.0f, 2.0f));
        values.add(new PointValue(2.0f, 2.5f));
        values.add(new PointValue(3.0f, 3.0f));

        //创建一条“线”，并且把“点”放到“线”中，并设置线的格式
        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_BLUE);
        line.setShape(ValueShape.CIRCLE);
        line.setHasPoints(true);
        line.setHasLabels(true);
        //创建“线”的集合：
        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(line);
        //创建“数据”，把“线”的集合放入“数据”中：
        LineChartData data = new LineChartData(lines);
        //创建"轴"：
        Axis axisX = new Axis();
        Axis axisY = new Axis();
        axisX.setName("X");
        axisY.setName("Y");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        //最后，实例化图表对象并且为其设置数据：
        mChart = (LineChartView) findViewById(R.id.chart);
        mChart.setZoomEnabled(true);
        mChart.setLineChartData(data);
    }
}
