package com.bw.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bw.common.R;

public class MyProgressBar extends View {

    private int measuredWidth;
    private int measuredHeight;
    private int arc_color;
    private int circle_color;
    private float stroke_width;
    private float circle_width;
    private int progress=0;
    private int startAngle=0;
    private int stepAngle=1;
    private float offseAngle=0;
    private int progressAngle;
    private int textColor;
    private float textSize;


    public MyProgressBar(Context context) {
        super(context);
    }


    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx=measuredWidth/2;
        int cy=measuredHeight/2;
        float radius =circle_width/2;

        Paint paint = new Paint();
        paint.setColor(circle_color);
        paint.setStrokeWidth(stroke_width);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(cx,cy,radius,paint);  //绘制圆

        if (progress==0){
            return;

        }

        RectF rectF = new RectF(measuredWidth/2-radius,measuredHeight/2-radius,measuredWidth/2+radius,measuredHeight/2+radius);

        paint.setColor(arc_color);
        canvas.drawArc(rectF,0,progressAngle,false,paint);

        String progressText=progressAngle*100/360+"%";
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        paint.getTextBounds(progressText, 0, progressText.length(), rect);
        canvas.drawText(progressText, measuredWidth/2-rect.width()/2, measuredHeight/2 + rect.height()/2, paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr) {
        initAttrs(context,attrs);

    }

    private void initAttrs(Context context,AttributeSet attributeSet) {
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.MyProgressBar);
        arc_color = array.getColor(R.styleable.MyProgressBar_arc_color, Color.RED);
        circle_color = array.getColor(R.styleable.MyProgressBar_circle_color, Color.GREEN);
        stroke_width = array.getDimension(R.styleable.MyProgressBar_stroke_width, 5);
        circle_width = array.getDimension(R.styleable.MyProgressBar_circle_width, 25);
        textColor = array.getColor(R.styleable.MyProgressBar_text_color, Color.RED);
        textSize = array.getDimension(R.styleable.MyProgressBar_text_Size, 10);

    }

    public void setProgress(int progress){
        this.progress = progress;
        offseAngle=(360*progress)/100;
        progressAngle=startAngle;

        handler.sendEmptyMessage(1);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            invalidate();
            if (progressAngle<=offseAngle){
                progressAngle+=stepAngle;

                handler.sendEmptyMessageDelayed(1,10);
            }

        }
    };

}
