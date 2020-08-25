package com.p2p.bawei.p2pinvest1801.mvp.view.myview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewPage_01 extends ViewPager {

    public MyViewPage_01(@NonNull Context context) {
        super(context);
    }

    public MyViewPage_01(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float x;
    float y;



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                 x = ev.getX();
                 y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getX()-x)>Math.abs(ev.getY()-y)){
                    return true;
                }else if (Math.abs(ev.getX()-x)<Math.abs(ev.getY()-y)){
                    return false;
                }
                    break;
//                Log.e("cx", "onInterceptTouchEvent: "+x+"xxxx"+ev.getX() );
        }
        return false;
    }
}
