package com.p2p.bawei.p2pinvest1801.myview;

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


import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;
import java.util.Random;

public class HistogramVIew extends View {

    private static String ZHU = "1";
    private static String YUAN = "2";
    private static String ZHE = "3";
    private String strings[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F"};

    private int viewWidth;
    private int viewHeight;

    private String Y_title = "Y";
    private String X_title = "X";
    private int Text_size = 30;

    public void setText_size(int text_size) {
        Text_size = text_size;
    }

    public void setY_title(String y_title) {
        Y_title = y_title;
    }

    public void setX_title(String x_title) {
        X_title = x_title;
    }

    private List<Bean> changeable;
    private int changeNumber = 1;
    private int size;
    private boolean flag = false;
    private int max;
    private int max2;
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
            } else if (ZHE.equals(style)) {
                Zhe(canvas, rectF);
            }
        }

        if (!flag) {
            //重新绘制
            invalidate();
        }
    }

    private void Zhe(Canvas canvas, RectF rectF) {
        //点的大小
        int rectSize = 5;
        //设置X轴与Y轴距离屏幕的宽度
        int Y_line = 100;
        int X_line = 100;

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

        //取长和宽的90%为轴的长度
        float h = viewHeight * 0.9f;
        float w = viewWidth * 0.9f;

        //X轴与Y轴
        canvas.drawLine(X_line, viewHeight - Y_line, w, viewHeight - Y_line, paint);
        canvas.drawLine(X_line, viewHeight - Y_line, X_line, viewHeight - h - Y_line, paint);
        //X轴与Y轴的title
        paint.setTextSize(Text_size);
        canvas.drawText(Y_title, X_line, viewHeight - h - Y_line, paint);
        canvas.drawText(X_title, w, viewHeight - Y_line, paint);

        float old_Y = viewHeight - Y_line;
        float old_X = X_line;

        //当所输入的数据大于屏幕的高度时，获取集合中的最大值，进行等比缩小
        for (int i = 0; i < size; i++) {
            if (max <= changeable.get(i).getNumber()) {
                max = changeable.get(i).getNumber();
            }
        }
        max2 = max;
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
        float Y_T = viewHeight - (100 + viewHeight * 0.9f);
        float Y_C = viewHeight - Y_T - 100;
        float Y_S = Y_C / 10;
        int d = max2 / 10;

        paint.setTextSize(15);
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(X_line, viewHeight - Y_S * i - Y_line, X_line + 10, viewHeight - Y_S * i - Y_line, paint);
            canvas.drawText(String.valueOf(d * i), X_line - 25, viewHeight - Y_S * i - Y_line, paint);
        }
        int sum = 0;
        float X_T = viewWidth - X_line - w + 200;
        float X_C = viewWidth - X_T;
        float X_S = X_C / size;

        for (int i = 0; i < size; i++) {
            int number = changeable.get(i).getNumber();
            sum += number;
            rectF.left = X_S * (i + 1) + X_line;
            rectF.right = rectSize + X_S * (i + 1) + X_line;

            rectF.top = viewHeight - Y_S * number / max2 * 10 - Y_line - rectSize;
            rectF.bottom = viewHeight - Y_S * number / max2 * 10 - Y_line;

//            //通过随机数获取16进制的颜色编号
//            Random random = new Random();
//            StringBuilder s = new StringBuilder();
//            for (int k = 0; k < 6; k++) {
//                int c = random.nextInt(16);
//                s.append(strings[c]);
//            }Color.parseColor("#" + s.toString())



            canvas.drawRect(rectF, paint);

            float X = rectF.centerX();
            float Y = rectF.centerY();
            paint.setColor(imageColor);
            paint.setTextSize(15);
            //点的数值
            canvas.drawText(String.valueOf(changeable.get(i).getNumber()), X, Y, paint);
            //X下标
//            canvas.drawLine(X, viewHeight - 100, X, viewHeight - 110, paint);
            //名称
            canvas.drawText(String.valueOf(changeable.get(i).getTitle()), X, viewHeight - 80, paint);

            //折线
            paint.setStrokeWidth(8);
            canvas.drawLine(old_X, old_Y, X, Y, paint);
            old_X = X;
            old_Y = Y;
        }
        int ping = sum / size;
        float v1 = viewHeight - Y_S * ping / max2 * 10 - Y_line;
        Log.d("HistogramVIew", "v1:" + v1);
//        canvas.drawText(String.valueOf(ping), X_line - 25, v1, paint);
//        canvas.drawLine(X_line, v1, w, v1, paint);


        flag = true;
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
            canvas.drawLine(widthCenter + x, heightCenter + y, widthCenter + x + 100, heightCenter + y - 30, paint);
            canvas.drawText(changeable.get(i).getTitle(), widthCenter + x + 100, heightCenter + y - 30, paint);
        }
        flag = true;
    }
}
