package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;

public class LineCartView extends LineChart {
    public LineCartView(Context context) {
        super(context);
    }

    public LineCartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineCartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }


}
