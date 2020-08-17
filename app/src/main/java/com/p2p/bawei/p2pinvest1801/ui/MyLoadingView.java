package com.p2p.bawei.p2pinvest1801.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.p2p.bawei.p2pinvest1801.R;


public class MyLoadingView extends RelativeLayout {

    private AnimationDrawable animationDrawable;

    public MyLoadingView(Context context) {
        super(context);
        init(context);
    }

    public MyLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.myview_loading, this);
        ImageView iv =  findViewById(R.id.animMy);
        animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
    }

    public void stop(){
        animationDrawable.stop();
    }
}
