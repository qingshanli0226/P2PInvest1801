package com.p2p.bawei.p2pinvest1801.invest.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyText extends androidx.appcompat.widget.AppCompatTextView {
    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
