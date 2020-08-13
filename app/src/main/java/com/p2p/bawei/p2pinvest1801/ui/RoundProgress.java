package com.p2p.bawei.p2pinvest1801.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.R;

public class RoundProgress extends View {

    private int circle_color;//圆形的颜色
    private int arc_color;//那个圆弧的颜色

    private int round_size;//圆环的宽度
    private float text_size;//字体大小
    private int text_color;//字体颜色

    private int stoke_width;//画笔的宽度
    private int max;// 圆环的最大值
    private int progress;//圆环的进度

    private int width;//当前视图的宽度
    private int height;//当前视图的高度

    private Paint paint;

    public RoundProgress(Context context) {
        super(context);
        init(context,null,0);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        circle_color = typedArray.getColor(R.styleable.RoundProgress_circle_color, Color.BLUE);
        arc_color = typedArray.getColor(R.styleable.RoundProgress_arc_color, Color.RED);
        round_size = typedArray.getInt(R.styleable.RoundProgress_round_size,10);
        text_size = typedArray.getDimension(R.styleable.RoundProgress_text_size,20);
        text_color = typedArray.getColor(R.styleable.RoundProgress_text_color,Color.BLUE);
        stoke_width = typedArray.getInt(R.styleable.RoundProgress_stoke_width,10);
        max = typedArray.getInt(R.styleable.RoundProgress_max,100);
        progress = typedArray.getInt(R.styleable.RoundProgress_progress,90);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();
        paint.setAntiAlias(true);//去掉毛边
        paint.setColor(circle_color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(round_size);

        //获取圆心的坐标
        int cx = width / 2;
        int cy = height / 2;

        float radius = width/2 - round_size/2;
        //绘制圆
        canvas.drawCircle(cx,cy,radius,paint);
        //绘制圆弧
        RectF rectF = new RectF(round_size/2,round_size/2,width-round_size/2,height-round_size/2);
        //设置颜色
        paint.setColor(arc_color);
        canvas.drawArc(rectF,0,(360*progress)/max,false,paint);

        //绘制文字
        String text = progress*100/max + "%";
        paint.setColor(text_color);
        paint.setTextSize(text_size);
        paint.setStrokeWidth(0);
        //创建一个矩形 没有具体的宽高
        Rect rect = new Rect();
        //矩形的宽高刚好包裹文本的宽高
        paint.getTextBounds(text,0,text.length(),rect);
        //获取左下角的坐标
        int x = width/2 - rect.width()/2;
        int y = height/2 + rect.height()/2;
        //绘制
        canvas.drawText(text,x,y,paint);
    }
}
