package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;

public class ProgressView extends View {
    private int circle_color;
    private int arc_color;
    private float stroke_width;
    private float text_size;
    private int text_color;
    int progressViewWidth;
    int progressViewHeight;
    int round_size;
    private float progress;
    String progresstext;
    public ProgressView(Context context) {
        super(context);
        init(context, null,0 );
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    public void init(Context context,AttributeSet attributeSet,int defStyleAttr){
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressView);
        circle_color = array.getColor(R.styleable.ProgressView_circle_color, Color.BLUE);
        arc_color = array.getColor(R.styleable.ProgressView_arc_color, Color.RED);
        stroke_width = array.getInt(R.styleable.ProgressView_stroke_width,1);
        text_size = array.getDimension(R.styleable.ProgressView_text_size,15.0f);
        text_color = array.getColor(R.styleable.ProgressView_text_color, Color.RED);
        round_size = array.getInt(R.styleable.ProgressView_round_size, 200);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = 0;
        int heightSize = 0;
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        if(widthmode == MeasureSpec.AT_MOST){
            widthSize = 300;
            progressViewWidth = 300;
        }else{
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
        }
        int heightmode = MeasureSpec.getMode(widthMeasureSpec);
        if(heightmode == MeasureSpec.AT_MOST){
            heightSize = 300;
            progressViewHeight = 300;
        }else{
            heightSize = MeasureSpec.getSize(heightMeasureSpec);
        }
        progressViewWidth = getMeasuredWidth();
        progressViewHeight = getMeasuredHeight();

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = progressViewWidth / 2;
        int cy = progressViewHeight / 2;
        float radius = round_size;

        Paint paint = new Paint();
        paint.setColor(circle_color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke_width);

        canvas.drawCircle(cx, cy, radius, paint);

        if(progress == 0){
            return;
        }

        RectF rectF = new RectF(progressViewWidth / 2 - round_size, progressViewHeight / 2 - round_size, progressViewWidth / 2 + round_size, progressViewWidth / 2 + round_size);
        paint.setColor(arc_color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke_width);
        canvas.drawArc(rectF, 0, progressAngle, false, paint);
        progresstext = (progressAngle*100)/360 +"%";

        Rect rect = new Rect();
        paint.setStrokeWidth(2);
        paint.setTextSize(text_size);
        paint.setColor(text_color);
        paint.getTextBounds(progresstext, 0, progresstext.length(), rect);
        canvas.drawText(progresstext, progressViewWidth/2-rect.width()/2, progressViewHeight/2+30-rect.height()/2, paint);

    }
    private int offseAngle;
    private int startAngle = 0;
    private int steptAngle = 1;
    private int progressAngle;
    public void setProgress(int progress){
        this.progress = progress;

        offseAngle = (360*progress)/100;
        progressAngle = startAngle;
        handler.sendEmptyMessage(1);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            invalidate();
            if(progressAngle <= offseAngle){
                progressAngle += steptAngle;
                handler.sendEmptyMessageDelayed(1,1);
            }
        }
    };
}
