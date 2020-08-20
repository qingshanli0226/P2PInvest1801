package com.example.common.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.common.R;

public class MyLoadingBar extends LinearLayout {

    public MyLoadingBar(Context context) {
        super(context);
    }

    public MyLoadingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this);

        ImageView iv = findViewById(R.id.my_loadingbar);
        AnimationDrawable drawable = (AnimationDrawable) iv.getDrawable();
        drawable.start();

    }
}
