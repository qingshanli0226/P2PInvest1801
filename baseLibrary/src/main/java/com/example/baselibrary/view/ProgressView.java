package com.example.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;


import androidx.annotation.Nullable;

import com.example.baselibrary.R;


public class ProgressView extends View {
    private int circleColor;
    private int arcColor;
    private int textColor;
    private float textSize;
    private float strokeWidth;
    private float roundSize;//圆形的大小
    //progressView的宽度和高度
    private int progressViewWidth;
    private int progressViewHeight;
    private int progress; //占比
    private int offseAngle;
    private int startAngle = 0;
    private int stepAngle = 1;
    private int progressAngle;

    private boolean flagBig = false;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs) {
        //获取属性的值,属性值是从布局文件里设置的
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        circleColor = typedArray.getColor(R.styleable.ProgressView_circle_color, Color.BLUE);
        arcColor = typedArray.getColor(R.styleable.ProgressView_arc_color, Color.RED);
        textColor = typedArray.getColor(R.styleable.ProgressView_text_color, Color.RED);
        textSize = typedArray.getDimension(R.styleable.ProgressView_text_size, 15.0f);
        strokeWidth = typedArray.getInt(R.styleable.ProgressView_stroke_width, 5);
        roundSize = typedArray.getDimension(R.styleable.ProgressView_round_size, 200.0f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        progressViewWidth = getMeasuredWidth();
        progressViewHeight = getMeasuredHeight();

        int widthSize;
        int heightSize;
        //在此处赋值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            //表示该空件在布局文件中，它的宽度是wrap_content
            if (flagBig) {
                widthSize = 500;
                progressViewWidth = 500;
                roundSize = 400;
            } else {
                widthSize = 300;
                progressViewWidth = 300;
            }

        } else {
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            //表示该空件在布局文件中，它的高度是wrap_content
            if (flagBig) {
                heightSize = 500;
                roundSize = 400;
                progressViewHeight = 500;
            } else {
                heightSize = 300;
                progressViewHeight = 300;
            }//针对高度是wrap_content, 给高度设置一个默认高度是300

        } else {
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
        }


        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取圆形的圆心
        int cX = progressViewWidth / 2;
        int cY = progressViewHeight / 2;
        float radius = roundSize / 2;//半径

        Paint paint = new Paint();
        paint.setAntiAlias(true);//去锯齿
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        canvas.drawCircle(cX, cY, radius, paint);//绘制蓝色的圆

        if (progress == 0) {
            return;
        }
        RectF rectF = new RectF((progressViewWidth/2) - roundSize / 2, (progressViewHeight >> 1) - roundSize / 2, (progressViewWidth >> 1) + roundSize / 2, (progressViewHeight >> 1) + roundSize / 2);
        paint.setColor(arcColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF, 0, progressAngle, false, paint);
        String progressText = (progressAngle * 100) / 360 + "%";
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(2);
        Rect rect = new Rect();
        paint.getTextBounds(progressText, 0, progressText.length(), rect);
        canvas.drawText(progressText, (progressViewWidth >> 1) - (rect.width() >> 1), (progressViewHeight >> 1) + (rect.height() >> 1), paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;

        offseAngle = (360 * progress) / 100;//扇形总的角度
        progressAngle = startAngle;
        handler.sendEmptyMessage(1);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            invalidate();//只是触发onDraw方法
            if (progressAngle <= offseAngle) {
                progressAngle += stepAngle;
                handler.sendEmptyMessage(1);
            }

        }
    };


}
