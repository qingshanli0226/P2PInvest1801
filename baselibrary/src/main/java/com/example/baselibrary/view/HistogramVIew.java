package com.example.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.baselibrary.R;
import com.example.baselibrary.bean.Bean;

import java.util.List;
import java.util.Random;

public class HistogramVIew extends View {

    private static String ZHU = "1";
    private static String YUAN = "2";
    private String strings[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F"};

    private int viewWidth;
    private int viewHeight;

    private List<Bean> changeable;
    private int changeNumber = 1;
    private int size;
    private boolean flag = false;
    private int max;
    private int imageColor, titleColor, numberColor;
    private String style;

    public void setList(List<Bean> list) {
        this.changeable = list;
    }

    public HistogramVIew(Context context) {
        super(context);
    }

    public HistogramVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initData() {
        //获取到用户输入的数据
        if (changeable != null) {
            size = changeable.size();
        }
    }

    @SuppressLint("Recycle")
    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HistogramVIew);
        imageColor = typedArray.getColor(R.styleable.HistogramVIew_image_color, Color.GRAY);
        titleColor = typedArray.getColor(R.styleable.HistogramVIew_title_color, Color.BLUE);
        numberColor = typedArray.getColor(R.styleable.HistogramVIew_number_color, Color.YELLOW);
        style = typedArray.getString(0);
    }

    public HistogramVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            viewWidth = 500;
        } else {
            viewWidth = widthSize;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            viewHeight = 500;
        } else {
            viewHeight = heightSize;
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initData();
        RectF rectF = new RectF();
        //当用户输入数据后进行绘制
        if (size != 0) {
            if (ZHU.equals(style)) {
                Zhu(canvas, rectF);
            } else if (YUAN.equals(style)) {
                Yuan(canvas, rectF);
            }
        }

        if (!flag) {
            //重新绘制
            invalidate();
        }
    }

    private void Zhu(Canvas canvas, RectF rectF) {
        int space = viewWidth / size;//每一个柱状图所占的大小
        int width = space / size;
        int jianJu = viewWidth / 50;//计算一个间距
        int roundSize = space / 2;  // 获取绘制的Y轴

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setTextSize(20);
        //当所输入的数据大于屏幕的高度时，获取集合中的最大值，进行等比缩小
        for (int i = 0; i < size; i++) {
            if (max <= changeable.get(i).getNumber()) {
                max = changeable.get(i).getNumber();
            }
        }
        //进行等比缩小
        while (true) {
            if (max >= viewHeight) {
                max = 0;
                for (int i = 0; i < size; i++) {
                    int number = changeable.get(i).getNumber();
                    changeable.get(i).setNumber(number / 2);
                    if (max <= changeable.get(i).getNumber()) {
                        max = changeable.get(i).getNumber();
                    }
                }
                changeNumber++;
            } else {
                break;
            }
        }

        if (size > 2) {
            for (int i = 0; i < size; i++) {
                int left = roundSize + i * space - width - jianJu;
                int top = viewHeight - 100 - changeable.get(i).getNumber();
                int right = roundSize + i * space + width + jianJu;


                rectF.bottom = viewHeight - 100;
                rectF.left = left;
                rectF.right = right;
                rectF.top = top;

                paint.setColor(imageColor);
                canvas.drawRect(rectF, paint);

                paint.setColor(numberColor);
                canvas.drawText(String.valueOf(changeable.get(i).getNumber() * changeNumber * 2), left, top - 3, paint);

                paint.setColor(titleColor);
                canvas.drawText(changeable.get(i).getTitle(), left, viewHeight - 80, paint);
            }
        } else {
            for (int i = 0; i < size; i++) {
                int left = roundSize + i * space - width - jianJu + 50;
                int top = viewHeight - 100 - changeable.get(i).getNumber();
                int right = roundSize + i * space + width + jianJu - 50;

                rectF.bottom = viewHeight - 100;
                rectF.left = left;
                rectF.right = right;
                rectF.top = top;

                paint.setColor(imageColor);
                canvas.drawRect(rectF, paint);

                paint.setColor(numberColor);
                canvas.drawText(String.valueOf(changeable.get(i).getNumber() * changeNumber * 2), left, top - 3, paint);

                paint.setColor(titleColor);
                canvas.drawText(changeable.get(i).getTitle(), left, viewHeight - 80, paint);
            }
        }
        flag = true;
    }

    private void Yuan(Canvas canvas, RectF rectF) {
        int sum = 0;

        //获取控件的中心位置
        int widthCenter = viewWidth / 2;
        int heightCenter = viewHeight / 2;
        //半径
        int r = widthCenter - 10;
        //确定控件的位置
        rectF.top = heightCenter - r / 2;
        rectF.bottom = heightCenter + r / 2;
        rectF.left = widthCenter - r / 2;
        rectF.right = widthCenter + r / 2;

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < size; i++) {
            sum += changeable.get(i).getNumber();
        }
        Log.e("hq", "Yuan: " + sum);
        float start = 0;
        float end = 0;
        for (int i = 0; i < size; i++) {
            //通过随机数获取16进制的颜色编号
            Random random = new Random();
            StringBuilder s = new StringBuilder();
            for (int k = 0; k < 6; k++) {
                int c = random.nextInt(16);
                s.append(strings[c]);
            }
            paint.setColor(Color.parseColor("#" + s.toString()));

            start += end;
            end = changeable.get(i).getNumber() * 1.0f / sum * 360;
            canvas.drawArc(rectF, start, end, true, paint);
            paint.setTextSize(20);
            float textAngle = start;
            float x = (float) (r / 2 * Math.cos(textAngle * Math.PI / 180));    //计算文字位置坐标
            float y = (float) (r / 2 * Math.sin(textAngle * Math.PI / 180));
            paint.setStrokeWidth(1);
            canvas.drawLine(widthCenter + x, heightCenter + y, widthCenter + x + 100, heightCenter + y-30, paint);
            canvas.drawText(changeable.get(i).getTitle(), widthCenter + x + 100, heightCenter + y-30, paint);
        }
        flag = true;
    }
}
