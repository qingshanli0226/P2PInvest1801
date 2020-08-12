package com.bw.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ToolBar extends LinearLayout {
    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }


    public ToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr) {
        
    }


}
