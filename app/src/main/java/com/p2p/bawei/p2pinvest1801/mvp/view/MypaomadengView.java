package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MypaomadengView extends AppCompatTextView {
    public MypaomadengView(Context context) {
        super(context);
    }

    public MypaomadengView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MypaomadengView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
