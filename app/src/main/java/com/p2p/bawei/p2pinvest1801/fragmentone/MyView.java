package com.p2p.bawei.p2pinvest1801.fragmentone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {

    private float cx;
    private float cy;
    private float radius;
    private Paint paint;
    private int sweepAngle;
    private int color;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.MyView);
        //获取颜色，圆的颜色
        color=a.getColor(R.styleable.MyView_circle_color, Color.BLACK);
        //圆的半径
        radius=a.getDimension(R.styleable.MyView_circle_radius,100);
        //圆的距离
        cx=a.getDimension(R.styleable.MyView_circle_x,200);
        cy=a.getDimension(R.styleable.MyView_circle_y,150);
        a.recycle();
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(sweepAngle>360){
                    return;
                }
                sweepAngle+=1;
                postInvalidate();
            }
        },1000,20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        paint.setStrokeWidth(20);
        canvas.drawCircle(cx,cy,radius,paint);
        //进度条走动颜色
        paint.setColor(Color.RED);
        RectF rectF=new RectF(cx - radius, cy - radius, cx + radius, cy + radius);
        canvas.drawArc(rectF, -90, sweepAngle, false, paint);
        int progress= (int) (sweepAngle/360f*100);
        paint.setStrokeWidth(0);
        //百分数颜色
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText(progress+"%",cx,cy,paint);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
