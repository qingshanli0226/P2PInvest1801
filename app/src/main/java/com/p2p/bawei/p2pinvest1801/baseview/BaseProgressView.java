package com.p2p.bawei.p2pinvest1801.baseview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;

public class BaseProgressView extends View {

    private int circleColor;//圆的颜色
    private float circleSize;//圆的宽
    private int textColor;//文字的颜色
    private int textSize;//文字的宽
    private int sectorColor;//扇形的颜色
    private int sectorSize;//扇形的宽
    private int diameter;//直径

    private int sweepAngle = 0;//扇形角度
    private int sweepAngleNum;//扇形角度每次增加的值
    private int sweepAngleMax;//扇形角度最终值

    private int progress = 0;//百分比

    private int progressViewWidth;
    private int progressViewHight;


    public BaseProgressView(Context context) {
        super(context);
    }

    public BaseProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public BaseProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context,AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BaseProgressView);
        circleColor = typedArray.getColor(R.styleable.BaseProgressView_circleColor, Color.RED);
        circleSize = typedArray.getFloat(R.styleable.BaseProgressView_circleSize, 30);
        textColor = typedArray.getColor(R.styleable.BaseProgressView_textColor, Color.RED);
        textSize = typedArray.getInt(R.styleable.BaseProgressView_textSize, 2);
        sectorColor = typedArray.getColor(R.styleable.BaseProgressView_sectorColor, Color.RED);
        sectorSize = typedArray.getInt(R.styleable.BaseProgressView_sectorSize,30);
        diameter = typedArray.getInt(R.styleable.BaseProgressView_diameter,30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件宽高
        progressViewWidth = getMeasuredWidth();
        progressViewHight = getMeasuredHeight();
        Log.e("BaseProgressView -> onMeasure", "宽:"+progressViewWidth+"高:"+progressViewHight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画第一个圆
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
        paint.setStrokeWidth(circleSize);
        canvas.drawCircle(progressViewWidth/2,progressViewHight/2,diameter / 2,paint);
        //画弧形
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(sectorColor);
        paint1.setStrokeWidth(sectorSize);
        canvas.drawArc(progressViewWidth/2 - diameter / 2 ,progressViewHight/2 - diameter / 2,progressViewWidth/2 + diameter / 2,progressViewHight/2 +  diameter / 2,0,sweepAngle,false,paint1);

        if (progress==0){
            return;
        }

        //画文字
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(textColor);
        paint2.setStrokeWidth(5);
        paint2.setTextSize(textSize);
        int text = (sweepAngle*100)/360;
        canvas.drawText(text+"%",progressViewWidth/2 - diameter / 8 ,progressViewHight/2 ,paint2);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
            if (sweepAngle  <= sweepAngleMax ){
                sweepAngleNum++;
                sweepAngle+=sweepAngleNum;
                handler.sendEmptyMessageDelayed(1,100);
            }
        }
    };

    public void setProgress(int progress) {
        this.progress = progress;
        sweepAngleMax = (360*progress)/100 ;
        handler.sendEmptyMessage(1);
    }
}
