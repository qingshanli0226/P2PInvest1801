package com.p2p.bawei.p2pinvest1801.custom;

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
    private float stokeSize;
    private int roundSize;  //圆的直径
    private int textColor;
    private float textSize;

    private int height;
    private int width;

    private int progressAngle = 0; //进度变化
    private int endProgressAngle = 0; //最终进度


    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        circleColor = typedArray.getColor(R.styleable.ProgressView_circleColor, Color.BLACK);
        arcColor = typedArray.getColor(R.styleable.ProgressView_arcColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor, Color.BLACK);
        stokeSize = typedArray.getFloat(R.styleable.ProgressView_stokeSize, 10.0f);
        roundSize = typedArray.getInt(R.styleable.ProgressView_roundSize, 200);
        textSize = typedArray.getDimension(R.styleable.ProgressView_textSize, 25.0f);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();

        Log.i("liu", "onMeasure: " + width);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(circleColor);
        paint.setStrokeWidth(stokeSize);

        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int halfRoundSize = roundSize / 2;

        canvas.drawCircle(halfWidth, halfHeight, halfRoundSize, paint);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(arcColor);
        paint1.setStrokeWidth(stokeSize);
        canvas.drawArc(halfWidth - halfRoundSize,
                halfHeight - halfRoundSize,
                halfWidth + halfRoundSize,
                halfHeight + halfRoundSize,
                0, progressAngle, false, paint1);

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(arcColor);
        paint2.setTextSize(textSize);

        String text = progressAngle * 100 / 360 + "%";

//        文字的x轴坐标
        float stringWidth = paint2.measureText(text);
        float x = (getWidth() - stringWidth) / 2;
        //文字的y轴坐标
        Paint.FontMetrics fontMetrics = paint2.getFontMetrics();
        float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(text, x, y, paint2);


    }

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            invalidate();

            if (progressAngle <= endProgressAngle) {
                progressAngle++;
                handler.sendEmptyMessageDelayed(1, 20);
            }

        }
    };

    public void setProgress(int progress) {
        endProgressAngle = (360 * progress) / 100; //获取最终值
        handler.sendEmptyMessage(1);

    }
}
