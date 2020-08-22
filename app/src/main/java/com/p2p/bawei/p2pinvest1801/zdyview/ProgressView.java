package com.p2p.bawei.p2pinvest1801.zdyview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.p2p.bawei.p2pinvest1801.R;

public class ProgressView extends View {
    private int circleColor;
    private int arcColor;
    private int roundize;
    private float stokeSize;
    private int textColor;
    private float textSize;

    private int progressViewWidth;
    private int progressViewHeight;

    private int step=1;
    private int offet=0;
    private int progressAngle;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void  init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        circleColor=typedArray.getColor(R.styleable.ProgressView_circleColor, Color.BLUE);
        arcColor=typedArray.getColor(R.styleable.ProgressView_arcColor, Color.RED);
        roundize=typedArray.getInt(R.styleable.ProgressView_roundSize, 150);
        stokeSize=typedArray.getFloat(R.styleable.ProgressView_stokeSize, 10);
        textColor=typedArray.getColor(R.styleable.ProgressView_textColor, Color.GREEN);
        textSize=typedArray.getDimension(R.styleable.ProgressView_textSize, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        progressViewHeight=getMeasuredHeight();
        progressViewWidth=getMeasuredWidth();
        Log.i("zcx", "onDraw: "+progressViewWidth+"----"+progressViewHeight);
    }

    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
        paint.setStrokeWidth(stokeSize);
        canvas.drawCircle(progressViewWidth/2,progressViewHeight/2,roundize/2,paint);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(arcColor);
        paint1.setStrokeWidth(stokeSize);
        canvas.drawArc(progressViewWidth/2-roundize/2,progressViewHeight/2-roundize/2,progressViewWidth/2+roundize/2,progressViewHeight/2+roundize/2,10,progressAngle,false,paint1);

        Rect rect = new Rect();
        Paint paint2 = new Paint();
        paint2.setStrokeWidth(2);
        paint2.setTextSize(textSize);
        String text=(progressAngle*100)/360+" %";
        paint2.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,(progressViewWidth/2)-(roundize/8),(progressViewWidth/2)+(roundize/8),paint2);


    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            invalidate();
            if (progressAngle<=offet){
                progressAngle+=step;
                handler.sendEmptyMessageDelayed(1,20);
            }
        }
    };
    public void setprogress(int progress){
            offet=(360*progress)/100;
            handler.sendEmptyMessage(1);

    }
}
