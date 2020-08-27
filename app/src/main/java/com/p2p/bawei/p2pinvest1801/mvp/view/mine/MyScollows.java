package com.p2p.bawei.p2pinvest1801.mvp.view.mine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;

import com.p2p.bawei.p2pinvest1801.R;

public class MyScollows extends ScrollView {
    public MyScollows(Context context) {
        super(context);
        init(context,null,0);
    }

    public MyScollows(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MyScollows(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    private LinechartDemoView touzi;
    private void init(Context context,AttributeSet attrs,  int defStyleAttr) {
        LayoutInflater from = LayoutInflater.from(context);
        View inflate = from.inflate(R.layout.myscollview, this);
        touzi = inflate.findViewById(R.id.touzi);
        initLinechart();
    }

    private void initLinechart() {
        touzi.setInfo(
                new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Now", "Dec"},
                new String[]{"0", "20", "40", "60", "80", "100"},
                new String[]{
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100),
                        "" + (int)(Math.random() * 100)
                },
                ""
        );
    }

}
