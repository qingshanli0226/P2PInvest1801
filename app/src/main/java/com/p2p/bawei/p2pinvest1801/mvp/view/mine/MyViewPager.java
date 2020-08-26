package com.p2p.bawei.p2pinvest1801.mvp.view.mine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    private int startx;
    private int starty;
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startx = (int) ev.getX();
                starty = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(ev.getX() - startx) > Math.abs(ev.getY() - starty)){
                    return true;
                }
                break;
        }
        return false;
    }
}
