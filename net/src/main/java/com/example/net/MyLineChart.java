package com.example.net;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;

public class MyLineChart extends LineChart {
    public MyLineChart(Context context) {
        super(context);
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_UP:
                            //告诉外部不要拦截
                            getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            //告诉外部要拦截
                            getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            });
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
