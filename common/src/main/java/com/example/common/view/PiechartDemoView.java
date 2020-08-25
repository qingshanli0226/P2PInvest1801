package com.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 饼状图view
 */
public class PiechartDemoView extends View {
    private int mHeight, mWidth;//宽高
    private Paint mPaint;//扇形的画笔
    private Paint mTextPaint;//画文字的画笔

    private int centerX, centerY;//中心坐标

    private int maxNum = 4;//扇形图的最大块数,超出部分自动合并到最后一块上去

    double total;//数据的总和
    double[] datas;//数据集
    String[] texts;//每个数据对应的文字集

    //颜色 默认的颜色
    private int[] mColors = {
            Color.parseColor("#FFC65B"), Color.parseColor("#FD5998"),
            Color.parseColor("#8971FB"), Color.parseColor("#676974")
    };

    private int mTextSize;//文字大小

    private int radius = 1000;//半径

    public PiechartDemoView(Context context) {
        super(context);
    }

    public PiechartDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init() {
        mTextSize = 25;

        mPaint = new Paint();
        //当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式， 如圆形样Cap.ROUND,或方形样式Cap.SQUARE
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        mPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        //设置绘制文字的字号大小
        mTextPaint.setTextSize(mTextSize);
        //当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
        mTextPaint.setStrokeWidth(3);
        //设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        mTextPaint.setAntiAlias(true);
        //设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。
        mTextPaint.setColor(Color.WHITE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高 不要设置wrap_content
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //无数据
        if (datas == null || datas.length == 0) return;

        centerX = (getRight() - getLeft()) / 2;
        centerY = (getBottom() - getTop()) / 2;
        int min = mHeight > mWidth ? mWidth : mHeight;
        if (radius > min / 2) {
            radius = (int) ((min - getPaddingTop() - getPaddingBottom()) / 3.5);
        }

        //画扇形
        canvas.save();
        drawCircle(canvas);
        canvas.restore();


        //线与文字
        canvas.save();
        drawLineAndText(canvas);
        canvas.restore();

    }


    //画线与文字
    private void drawLineAndText(Canvas canvas) {
        int start = 0;
        canvas.translate(centerX, centerY);//平移画布到中心
        mPaint.setStrokeWidth(4);
        for (int i = 0; i < (maxNum < datas.length ? maxNum : datas.length); i++) {

            if (i == (maxNum < datas.length ? maxNum : datas.length) - 1){
                drawLine(canvas, start, 360 - start, texts[i], mColors[i % mColors.length],i);
            }else {
                float angles = (float) ((datas[i] * 1.0f / total) * 360);
                drawLine(canvas, start, angles, texts[i], mColors[i % mColors.length],i);
                start += angles;
            }
        }
    }

    private void drawLine(Canvas canvas, int start, float angles, String text, int color,int position) {
        mPaint.setColor(color);
        //mTextPaint.setColor(color);
        float stopX, stopY;
        stopX = (float) ((radius + 40) * Math.cos((2 * start + angles) / 2 * Math.PI / 180));
        stopY = (float) ((radius + 40) * Math.sin((2 * start + angles) / 2 * Math.PI / 180));

        switch (position){
            case 0:
                canvas.drawLine((float) ((radius * 0.5) * Math.cos((2 * start + angles) / 2 * Math.PI / 180)),
                        (float) ((radius * 0.5) * Math.sin((2 * start + angles) / 2 * Math.PI / 180)),
                        stopX, stopY, mPaint);
                break;
            case 1:
                canvas.drawLine((float) ((radius * 0.7) * Math.cos((2 * start + angles) / 2 * Math.PI / 180)),
                        (float) ((radius * 0.7) * Math.sin((2 * start + angles) / 2 * Math.PI / 180)),
                        stopX, stopY, mPaint);
                break;
            case 2:
                canvas.drawLine((float) ((radius * 1.1) * Math.cos((2 * start + angles) / 2 * Math.PI / 180)),
                        (float) ((radius * 1.1) * Math.sin((2 * start + angles) / 2 * Math.PI / 180)),
                        stopX, stopY, mPaint);
                break;
            case 3:
                canvas.drawLine((float) ((radius * 0.9) * Math.cos((2 * start + angles) / 2 * Math.PI / 180)),
                        (float) ((radius * 0.9) * Math.sin((2 * start + angles) / 2 * Math.PI / 180)),
                        stopX, stopY, mPaint);
                break;
            default:
                canvas.drawLine((float) (radius * Math.cos((2 * start + angles) / 2 * Math.PI / 180)),
                        (float) (radius * Math.sin((2 * start + angles) / 2 * Math.PI / 180)),
                        stopX, stopY, mPaint);
                break;
        }
        //canvas.drawLine(0, 0, stopX, stopY, mPaint);
        //画横线
        int dx;//判断横线是画在左边还是右边
        int endX;
        if (stopX > 0) {
            endX = (int) (stopX + 110);
        } else {
            endX = (int) (stopX - 110);
        }
        //画横线
        canvas.drawLine(stopX, stopY,
                endX, stopY, mPaint
        );
        dx = (int) (endX - stopX);

        //测量文字大小
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        int w = rect.width();
        int h = rect.height();
        int offset = 20;//文字在横线的偏移量
        //画文字
        canvas.drawText(text, 0, text.length(), dx > 0 ? stopX + offset : stopX - w - offset, stopY - 5, mTextPaint);

        //测量百分比大小
        String percentage = (datas[position] / total) * 100 + "";
        percentage = percentage.substring(0, percentage.length() > 4 ? 4 : percentage.length()) + "%";
        mTextPaint.getTextBounds(percentage, 0, percentage.length(), rect);
        w = rect.width() - 10;
        //画百分比
        canvas.drawText(percentage, 0, percentage.length(), dx > 0 ? stopX + offset : stopX - w - offset, stopY + h, mTextPaint);

    }

    //画扇形
    private void drawCircle(Canvas canvas) {
        RectF rect = null;
        int start = 0;
        for (int i = 0; i < (maxNum < datas.length ? maxNum : datas.length); i++) {
            if (i == 0){
                rect = new RectF((float) (centerX - radius * 0.5), (float) (centerY - radius * 0.5),
                        (float) (centerX + radius * 0.5), (float) (centerY + radius * 0.5));
            }else if (i == 1){
                rect = new RectF((float) (centerX - radius * 0.7), (float) (centerY - radius * 0.7),
                        (float) (centerX + radius * 0.7), (float) (centerY + radius * 0.7));
            }else if (i == 2){
                rect = new RectF((float) (centerX - radius * 1.1), (float) (centerY - radius * 1.1),
                        (float) (centerX + radius * 1.1), (float) (centerY + radius * 1.1));
            }else if (i == 3){
                rect = new RectF((float) (centerX - radius * 0.9), (float) (centerY - radius * 0.9),
                        (float) (centerX + radius * 0.9), (float) (centerY + radius * 0.9));
            }else {
                rect = new RectF((float) (centerX - radius * 1), (float) (centerY - radius * 1),
                        (float) (centerX + radius * 1), (float) (centerY + radius * 1));
            }

            if (i == (maxNum < datas.length ? maxNum : datas.length) - 1){
                float angles = 360 - start;
                mPaint.setColor(mColors[i % mColors.length]);

                if (i == 2){
                    canvas.drawArc(rect, start, angles, true, mPaint);

                    mPaint.setColor(Color.parseColor("#34374A"));
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeWidth(8);
                    rect = new RectF((float) (centerX - radius * 1.03), (float) (centerY - radius * 1.03),
                            (float) (centerX + radius * 1.03), (float) (centerY + radius * 1.03));

                    canvas.drawArc(rect, start, angles, false, mPaint);
                    init();
                }else {
                    canvas.drawArc(rect, start, angles, true, mPaint);
                }

                start += angles;
            }else {
                float angles = (float) ((datas[i] * 1.0f / total) * 360);
                mPaint.setColor(mColors[i % mColors.length]);

                if (i == 2){
                    canvas.drawArc(rect, start, angles, true, mPaint);

                    mPaint.setColor(Color.parseColor("#34374A"));
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setStrokeWidth(8);
                    rect = new RectF((float) (centerX - radius * 1.03), (float) (centerY - radius * 1.03),
                            (float) (centerX + radius * 1.03), (float) (centerY + radius * 1.03));

                    canvas.drawArc(rect, start, angles, false, mPaint);
                    init();
                }else {
                    canvas.drawArc(rect, start, angles, true, mPaint);
                }


                start += angles;
            }
        }
    }

    //setter
    public void setColors(int[] mColors) {
        this.mColors = mColors;
        invalidate();
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        mTextPaint.setTextSize(mTextSize);
        invalidate();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
        invalidate();
    }

    public void setDatas(double[] datas) {
        this.datas = datas;
        total = 0;
        for (int i = 0;i < datas.length;i++){
            total += datas[i];
        }
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }
}
