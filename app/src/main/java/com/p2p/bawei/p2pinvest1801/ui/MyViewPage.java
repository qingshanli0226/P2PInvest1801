package com.p2p.bawei.p2pinvest1801.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


public class MyViewPage extends ViewPager {

    //down落下时的位置
    private int startX;
    private int startY;

    public MyViewPage(@NonNull Context context) {
        super(context);
    }

    public MyViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) getX();
                startY = (int) getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(ev.getX() - startX) > Math.abs(ev.getY() - startY)){
                    return true;
                }
                break;
        }
        return false;
    }
}
