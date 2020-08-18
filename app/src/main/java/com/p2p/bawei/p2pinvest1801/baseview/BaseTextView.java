package com.p2p.bawei.p2pinvest1801.baseview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class BaseTextView extends AppCompatTextView {
    public BaseTextView(Context context) {
        super(context);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
