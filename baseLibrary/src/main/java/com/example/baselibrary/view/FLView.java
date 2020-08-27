package com.example.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class FLView extends View {
    private int wSize, hSize;
    private String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f"};
    private int X;
    private int Y;
    private float upY, downY;
    private List<String> list;
    private int start = 0, end = 5;

    public void setList(List<String> list) {
        this.list = list;
    }

    public FLView(Context context) {
        super(context);
    }

    public FLView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FLView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            upY = event.getY();
            if (downY < upY) {
                start -= 5;
                end -= 5;
                if (start <= 0) {
                    start = 0;
                    end = 5;
                }
            } else {
                start += 5;
                end += 5;
                if (end > list.size() + 1) {
                    start = 0;
                    end = 5;
                }
            }
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downY = event.getY();
            Log.e("hq", "onTouchEvent: " + downY + "-----" + upY);

        }

        return true;
    }

    public void start() {
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        wSize = MeasureSpec.getSize(widthMeasureSpec);
        if (wMode == MeasureSpec.AT_MOST) {
            wSize = 500;
        }
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        hSize = MeasureSpec.getSize(heightMeasureSpec);
        if (hMode == MeasureSpec.AT_MOST) {
            hSize = 500;
        }
        setMeasuredDimension(wSize, hSize);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Random random = new Random();

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setTextSize(20);
        for (int i = start; i < end; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#");
            for (int k = 0; k < 6; k++) {
                int i1 = random.nextInt(16);
                String s = str[i1];
                stringBuilder.append(s);
            }
            paint.setColor(Color.parseColor(stringBuilder.toString()));
            int oldX = X;
            int oldY = Y;
            X = random.nextInt(wSize);
            Y = random.nextInt(hSize);

            int size = random.nextInt(50);
            if (size < 30) {
                size = 30;
            }
            paint.setTextSize(size);
            int textWidth = getTextWidth(list.get(i), paint);
            int textHeight = getTextHeight(list.get(i), paint);
            if (wSize - X < textWidth) {
                X -= textWidth;
            }
            if (hSize - Y < textHeight) {
                Y -= textHeight;
            }
            canvas.drawText(list.get(i), X, Y, paint);

        }

    }

    private int getTextWidth(String text, Paint paint) {
        Rect rect = new Rect(); // 文字所在区域的矩形
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();
    }

    private int getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }
}
