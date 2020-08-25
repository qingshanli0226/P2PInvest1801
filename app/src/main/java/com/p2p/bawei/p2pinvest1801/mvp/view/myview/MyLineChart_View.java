package com.p2p.bawei.p2pinvest1801.mvp.view.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;

public class MyLineChart_View extends LineChart {
    public MyLineChart_View(Context context) {
        super(context);
    }

    public MyLineChart_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        getParent().requestDisallowInterceptTouchEvent(true);//不拦截
                        break;
                    case MotionEvent.ACTION_UP:
                        getParent().requestDisallowInterceptTouchEvent(false);//拦截
                        break;

                    default:
                        break;
                }
                return false;
            }
        });


    }

    public MyLineChart_View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
