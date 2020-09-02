package com.p2p.bawei.p2pinvest1801;

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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;

public class ProgressView extends View {

    private int circleColor;//圆颜色
    private int arcColor;//选中圆颜色
    private int textColor;//文字颜色
    private float textSize;//文字大小
    private float strokeWidth;//实线宽度
    private float roundSize;//圆形的大小
    private int progressViewHeight;//ProgressView高度
    private int progressViewWidth;//ProgressView宽度
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
        init(context,attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {

        //获取属性的值-从文件布局里设置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        circleColor = typedArray.getColor(R.styleable.ProgressView_circle_color, Color.BLUE);//获取圆颜色
        arcColor = typedArray.getColor(R.styleable.ProgressView_arc_color,Color.RED);//获取进度颜色
        textColor = typedArray.getColor(R.styleable.ProgressView_text_color,Color.CYAN);//获取文字颜色
        textSize = typedArray.getDimension(R.styleable.ProgressView_text_size, 40.0f);//文字大小
        strokeWidth = typedArray.getInt(R.styleable.ProgressView_stroke_width,5);//边框宽度
        roundSize = typedArray.getDimension(R.styleable.ProgressView_round_size, 200.0f);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度并赋值
        progressViewHeight = getMeasuredHeight();
        progressViewWidth = getMeasuredWidth();

        int widthSize = 0;//宽
        int heightSize = 0;//高

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST){
            if(flagBig){
                widthSize = 500;
                progressViewWidth = 500;
                roundSize = 400;
            }else {
                widthSize = 300;
                progressViewWidth = 300;
            }
        }else {
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(heightMode == MeasureSpec.AT_MOST){
            if(flagBig){
                heightSize = 500;
                roundSize = 400;
                progressViewHeight = 500;
            }else {//针对高度是wrap_content, 给高度设置一个默认高度是300
                heightSize = 300;
                progressViewHeight = 300;
            }
        }else {
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(widthSize,heightSize);
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
        int circle_X = progressViewWidth/2;
        int circle_Y = progressViewHeight/2;

        // radius--半径
        float radius = roundSize / 2;

        //画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);//去除锯齿
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
        paint.setStrokeWidth(strokeWidth);
        //绘制圆
        canvas.drawCircle(circle_X,circle_Y,radius,paint);

        if(progress == 0){
            return;
        }

        RectF rectF = new RectF(progressViewWidth / 2 - roundSize / 2, progressViewHeight / 2 - roundSize / 2, progressViewWidth / 2 + roundSize / 2, progressViewHeight / 2 + roundSize / 2);
        paint.setColor(arcColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF,0,progressAngle,false,paint);
        String progress_text = (progressAngle*100)/360+"%";
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(2);
        Rect rect = new Rect();
        paint.getTextBounds(progress_text,0,progress_text.length(),rect);
        canvas.drawText(progress_text,progressViewWidth/2-rect.width()/2,progressViewHeight/2 + rect.height()/2, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //扇形的总角度
        offseAngle = (360*progress)/100;
        progressAngle = startAngle;
        handler.sendEmptyMessage(1);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            invalidate();//触发onDraw方法
            if (progressAngle <= offseAngle) {
                progressAngle += stepAngle;
                handler.sendEmptyMessageDelayed(1, 20);
            }

        }
    };


    public void setFlagBig(boolean flagBig) {
        this.flagBig = flagBig;
//同时触发onMeasure，onLayout，onDraw方法
        requestLayout();
        progressAngle = 0;
        handler.sendEmptyMessageDelayed(1, 1000);
    }
}
