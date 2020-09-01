package com.p2p.bawei.p2pinvest1801.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://向下点击事件
                        getParent().requestDisallowInterceptTouchEvent(true);//告诉外部viewGroup不要拦截事件
                        break;
                    case MotionEvent.ACTION_UP://当手指抬起时，外部ViewGroup可以拦截事件
                        getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        break;

                }

                return false;
            }
        });

    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }




}
