package com.p2p.bawei.p2pinvest1801.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyDomeLinearLayout  extends LinearLayout {
    private int startX, startY;
    public MyDomeLinearLayout(Context context) {
        super(context);
    }

    public MyDomeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDomeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //外部拦截法，重写外部ViewGroup的拦截方法，根据滑动的情况判断当前是否拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int)ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getX() - startX) < Math.abs(ev.getY() - startY)) {
                    return true;
                }
                break;
        }
        return false;
    }
}
