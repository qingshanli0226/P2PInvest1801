package com.p2p.bawei.p2pinvest1801.mine.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ColumnChartActivity extends AppCompatActivity {
    //声明所需变量
    public final static String[] months = new String[]{"Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",};

    ColumnChartView columnChart;
    ColumnChartData columnData;
    List<Column> lsColumn = new ArrayList<Column>();
    List<SubcolumnValue> lsValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);

        columnChart = findViewById(R.id.columnchart);

        dataInit();
    }

    private void dataInit() {

        int numSubcolumns = 1;
        int numColumns = months.length;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5,
                        ChartUtils.pickColor()));
            }
// 点击柱状图就展示数据量
            axisValues.add(new AxisValue(i).setLabel(months[i]));

            columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
        }

        columnData = new ColumnChartData(columns);

        columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
                .setTextColor(Color.BLACK));
        columnData.setAxisYLeft(new Axis().setHasLines(true)
                .setTextColor(Color.BLACK).setMaxLabelChars(2));

        columnChart.setColumnChartData(columnData);

// Set value touch listener that will trigger changes for chartTop.
        columnChart.setOnValueTouchListener(new ValueTouchListener());

// Set selection mode to keep selected month column highlighted.
        columnChart.setValueSelectionEnabled(true);

        columnChart.setZoomType(ZoomType.HORIZONTAL);



    }

    /**
     * 柱状图监听器
     *
     * @author 1017
     */
    private class ValueTouchListener implements
            ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex,
                                    SubcolumnValue value) {
// generateLineData(value.getColor(), 100);
        }

        @Override
        public void onValueDeselected() {

// generateLineData(ChartUtils.COLOR_GREEN, 0);

        }
    }


}
