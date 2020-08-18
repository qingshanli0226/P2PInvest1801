package com.bw.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MyHotDanmuView extends ScrollView {
    private int measuredHeight;
    private int measuredWidth;
    private int textSize=25;
    private ArrayList<String> strings=new ArrayList<>();
    private int colors[]=new int[]{Color.GREEN,Color.BLUE,Color.GRAY,Color.RED};

    public MyHotDanmuView(Context context) {
        super(context);
    }

    public MyHotDanmuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHotDanmuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);

        Rect rect = new Rect();
        for (int i = 0; i < strings.size(); i++) {
            paint.setColor(colors[(int) (Math.random()*colors.length)]);
            String string = strings.get(i);
            paint.getTextBounds(string, 0, string.length(), rect);
            canvas.drawText(string, (int) (Math.random()*measuredWidth)+1-rect.width()/2, (int) (Math.random()*measuredHeight)+1 + rect.height()/2, paint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();

    }

    public void setTitles(ArrayList<String> titles){
        strings.clear();
        strings.addAll(titles);
    }

    public void ondestoryView(){

    }
}
