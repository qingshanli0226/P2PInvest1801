package com.p2p.bawei.p2pinvest1801.view.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SlidingViewPager extends ViewPager {
    private float x;
    private float y;
    public SlidingViewPager(Context context) {
        super(context);
    }

    public SlidingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                 x = event.getX();
                 y = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if(Math.abs(event.getX()-x)>Math.abs(event.getY()-y)){
                    return true;
                }
                break;
        }
        return false;
    }
}
