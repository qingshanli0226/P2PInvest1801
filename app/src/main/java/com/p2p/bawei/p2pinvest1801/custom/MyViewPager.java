package com.p2p.bawei.p2pinvest1801.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {

    private int startX;
    private int startY;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    //拦截器
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:  //最开始按下的时候
                //获取最开始的值
                startX = (int) ev.getX();
                startY = (int) ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                //判断距离  如果滑动的横向距离比竖向距离大 说明进行横向滑动 也就是ViewPager消费
                if (Math.abs(ev.getX() - startX) > Math.abs(ev.getY() - startY)) {
                    return true;
                }
                break;
        }
        return false;
    }
}
