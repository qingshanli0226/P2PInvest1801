package com.p2p.bawei.p2pinvest1801.home.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * 圆形进度条
 */
public class RoundProgressBar extends View {
    //绘制矩形区域
    private RectF rectF;
    //起始角度
    private float startAngle;
    //扫过角度
    private float sweepAngle;
    //画笔
    private Paint paint;
    //默认控件大小
    private int defoutSize;
    //默认线条宽度
    private int defoutLine;
    private int strokeWidth;

    private PointF pointF = new PointF();


    public RoundProgressBar(Context context) {
        super(context);
        initData();
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    /**
     * 参数初始化
     */
    private void initData() {
        startAngle = 0;
        sweepAngle = 0;
        defoutSize = 140;
        defoutLine = 10;
        strokeWidth = 10;

        rectF = new RectF();

        //抗锯齿画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(defoutLine);
        //笔帽样式
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * xml -----> 提供可绘制位置
     *
     * @param widthMeasureSpec  宽
     * @param heightMeasureSpec 高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(defoutSize, defoutSize);
    }

    /**
     * 当大小时改变回调
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        pointF.x = w >> 1;
        pointF.y = h >> 1;

        rectF.top = strokeWidth >> 1;
        rectF.bottom = h - (strokeWidth >> 1);
        rectF.left = strokeWidth >> 1;
        rectF.right = w - (strokeWidth >> 1);

    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画布旋转
        paint.setColor(Color.parseColor("#5A6E9E"));
        canvas.rotate(-360, pointF.x, pointF.y);
        //绘制圆环
        canvas.drawArc(rectF, startAngle, 360, false, paint);

        paint.setColor(Color.parseColor("#DC143C"));
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);

    }

    public void setProgress(float index) {
        //防止数值越界
        if (index > 1 || index < 0) {
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, index);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue() * 360;
                //重写绘制
                invalidate();
            }
        });
        valueAnimator.start();

    }
}
