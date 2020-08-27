package com.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱状图demo
 */
public class HistogramDemoView extends View {

    //画笔
    private Paint mLinePaint;
    private Paint mColorPaint;
    private Paint mTextPaint;
    //上下文
    private Context mContext;
    //定义宽高
    private float weight;
    private float height;
    private float mScale;
    //数组高度值
    private String[] y_title = new String[]{"100", "80", "60", "40", "20", "0"};
    //分别为定义数据与数据源名称的集合
    private List<Long> mData;
    private List<String> mNames;

    public HistogramDemoView(Context context) {
        super(context);
    }

    public HistogramDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //定义画笔
        mContext = context;
        mLinePaint = new Paint();
        mColorPaint = new Paint();
        mTextPaint = new Paint();

        mLinePaint.setARGB(255, 223, 233, 231);
        mColorPaint.setARGB(255, 0, 200, 149);
        mTextPaint.setARGB(255, 153, 153, 153);

        mColorPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setAntiAlias(true);
        mColorPaint.setAntiAlias(true);
        mLinePaint.setAntiAlias(true);

        mScale = context.getResources().getDisplayMetrics().density;
        //初始化数据
        mData = new ArrayList<>();
        mNames = new ArrayList<>();
    }

    public HistogramDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //尺寸改变时调用
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        weight =  0.7f*w;
        height = 0.7f* h;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float min_height = height / 5;
        for (int i = 5; i >= 0; i--) {
            if (i == 5) {
                mLinePaint.setARGB(255, 38, 134, 238);
            } else {
                mLinePaint.setARGB(255, 223, 233, 231);
            }
            canvas.drawLine(70 * mScale, 30 * mScale + min_height * i, 70 * mScale + weight, 30 * mScale + min_height * i, mLinePaint);
            mTextPaint.setTextAlign(Paint.Align.RIGHT);
            mTextPaint.setTextSize(10 * mScale);
            canvas.drawText(y_title[i], 60 * mScale, 32 * mScale + min_height * i, mTextPaint);
        }
        float min_weight = (weight - 5 * mScale) / (mData.size());
        mTextPaint.setTextSize(10 * mScale);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < mData.size(); i++) {
            int leftR = (int) (70 * mScale + i * min_weight + min_weight / 2);
            int rightR = leftR + (int) (min_weight / 2);
            int buttomR = (int) (30 * mScale + min_height * 5);
            int topR = buttomR - (int) (height / 100 * mData.get(i));
            canvas.drawRect(new RectF(leftR, topR, rightR, buttomR), mColorPaint);
            mTextPaint.setARGB(255, 90, 110, 158);
            canvas.drawText(mNames.get(i), leftR + min_weight / 4, buttomR + 20 * mScale, mTextPaint);
            mTextPaint.setARGB(255, 51, 51, 51);
            canvas.drawText(mData.get(i) + "", leftR + min_weight / 4, topR - 10 * mScale, mTextPaint);
        }
    }

    //传入数据并进行绘制
    public void updateThisData(List<Long> data, List<String> name) {
        mData = data;
        mNames = name;
        //重绘
        invalidate();
    }

}
