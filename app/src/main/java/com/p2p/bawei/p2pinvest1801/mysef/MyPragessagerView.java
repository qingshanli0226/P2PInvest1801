package com.p2p.bawei.p2pinvest1801.mysef;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.p2p.bawei.p2pinvest1801.R;

public class MyPragessagerView extends View {
    //圆的颜色
    private int circle_color;
    //圆的半径
    private float circle_radil;
    //进度条的颜色
    private int bar_color;
    //文字的颜色
    private int text_color;
    //文本的字体大小
    private float text_Size;

    //宽
    private int progesswidth;
    //高
    private int progesshight;
    //圆的宽
    private int progessStrokeWidth;
    //进度占比
    private Integer progress;
    //进度角度
    private int progressAngle;
    //总角度
    private int offseAngle;
    private int startAngle;
    //画笔
    private Paint paint=new Paint();
    private Paint paintq=new Paint();
    private Paint paintt=new Paint();
    public MyPragessagerView(Context context) {
        super(context);
    }

    public MyPragessagerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }



    public MyPragessagerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPragessagerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void init(Context context, AttributeSet attrs) {
        //获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyPragessagerView);
        //取出所有自定义的属性
        circle_color=typedArray.getColor(R.styleable.MyPragessagerView_cirloe_color, Color.GRAY);
        circle_radil=typedArray.getDimension(R.styleable.MyPragessagerView_circle_radil,10);
        text_color=typedArray.getColor(R.styleable.MyPragessagerView_text_color,Color.BLACK);
        bar_color=typedArray.getColor(R.styleable.MyPragessagerView_bar_color,Color.BLACK);
        text_Size=typedArray.getDimension(R.styleable.MyPragessagerView_text_size,2);
        progress=typedArray.getInteger(R.styleable.MyPragessagerView_progress,30);
        progessStrokeWidth=typedArray.getInteger(R.styleable.MyPragessagerView_progessstrokewidth,10);
        //回收处理
        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        //圆心半径
         float cx = width/2;
         float cy=height/2;
         //去锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(progessStrokeWidth);
        paint.setColor(circle_color);
        canvas.drawCircle(cx,cy,circle_radil,paint);
        if (progress==0){
            return;
        }
        //绘制圆圈
        paintq.setColor(bar_color);
        paintq.setStyle(Paint.Style.STROKE);
        paintq.setStrokeWidth(progessStrokeWidth);
        RectF rectF = new RectF(progesswidth/2-circle_radil,progesshight/2-circle_radil,progesswidth/2+circle_radil,progesshight/2+circle_radil);
        canvas.drawArc(rectF,0,progressAngle,false,paintq);
        //文字画笔
        paintt.setStrokeWidth(2);
        paintt.setColor(bar_color);
        paintt.setAntiAlias(true);
        paintt.setTextSize(text_Size);
        String text=(progressAngle*100)/360+"%";
        canvas.drawText(text,progesswidth/2-circle_radil/4,progesshight/2+circle_radil/8,paintt);
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
        offseAngle = (360*progress)/100;//扇形总的角度
        progressAngle=startAngle;
        handler.sendEmptyMessage(1);

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            invalidate();
            if (progressAngle<=offseAngle){
                progressAngle++;
                handler.sendEmptyMessageDelayed(1,20);
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        progesshight=getMeasuredHeight();
        progesswidth=getMeasuredWidth();
    }
}
