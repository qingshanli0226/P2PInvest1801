package com.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;
import java.util.TreeMap;

public class LinechartDemoView extends View {
    private Paint paint;
    private Paint dashedPaint;//虚线
    private Paint paintPoint;//点
    private Paint paintText;

    private int mXPoint = 100;//原点的X轴坐标
    private int mYPoint = 300;//原点的Y轴坐标
    private int mXScale = 50;//X轴的刻度长度
    private int mYScale = 55;//Y轴的刻度长度
    public int mXLength = 310;//X轴的长度
    private int mYLength = 310;//Y轴的长度

    private String[] mXLabel = null;//X轴上的刻度
    private String[] mYLabel = null;//Y轴上的刻度
    private String[] mData = null;//需要展示的数据
    private String mTitle = null;

    private float maxValue;//传入数据的最大值
    private int dataNum;//数据总数

    private float mRange[]=new float[3];

    // float y坐标 Integer y坐标对应的横线的颜色
    private TreeMap<Float,Integer> yCoords = new TreeMap<>();

    public void setInfo(String[] mXLable, String[] mYLable,String[] AllData,String strTitle) {
        mXLabel = mXLable;//横坐标
        mYLabel = mYLable;//纵坐标
        mData = AllData;//数据
        mTitle = strTitle;//表头
    }
    public void setRange(float[] range) {
        mRange = range;
    }
    public LinechartDemoView(Context context) {
        super(context);
    }
    public LinechartDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinechartDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);//设置线宽
        paint.setAntiAlias(true);//去锯齿
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(16);

        // 虚线
        dashedPaint = new Paint();
        dashedPaint.setAntiAlias(true);
        dashedPaint.setFilterBitmap(true);
        dashedPaint.setStyle(Paint.Style.STROKE);
        dashedPaint.setStrokeWidth(4);//设置线宽
        PathEffect effect = new DashPathEffect(new float[]{5,5,5,5},5);
        dashedPaint.setPathEffect(effect);

        //文字
        paintText = new Paint();
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setAntiAlias(true);//去锯齿
        paintText.setColor(Color.DKGRAY);
        paintText.setTextSize(16);

        //点
        paintPoint = new Paint();
        paintPoint.setStyle(Paint.Style.STROKE);
        paintPoint.setAntiAlias(true);//去锯齿
        paintPoint.setStyle(Paint.Style.FILL);//实心
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //根据数据的数量计算X轴的刻度长度
        calculateXScale();

        //设置虚线
        drawAboutYs(canvas);
        //设置Y轴
        canvas.drawLine(mXPoint, mYPoint, mXPoint, mYPoint-mYLength, paint);
        //设置Y轴上的刻度显示（如果没有for循环，将只会是一个Y轴的直线）
        for(int i = 0;i*mYScale<mYLength;i++){
            //画刻度线
            canvas.drawLine(mXPoint, mYPoint-i*mYScale, mXPoint+5, mYPoint-i*mYScale, paint);//刻度
            try {
                //画轴上的刻度值
                canvas.drawText(mYLabel[i], mXPoint-24, mYPoint-i*mYScale+5,paintText);//刻度
            } catch (Exception e) {
            }
        }
        canvas.drawLine(mXPoint, mYPoint-mYLength, mXPoint-3, mYPoint-mYLength+6, paint);//箭头
        canvas.drawLine(mXPoint, mYPoint-mYLength, mXPoint+3, mYPoint-mYLength+6, paint);
        //设置X轴
        canvas.drawLine(mXPoint, mYPoint, mXPoint+mXLength, mYPoint, paint);//画轴线
        for(int i = 0;i*mXScale<mXLength;i++){
            //画刻度
            canvas.drawLine(mXPoint+i*mXScale, mYPoint, mXPoint+i*mXScale, mYPoint - 5, paint);
            try {
                //画刻度值
                canvas.drawText(mXLabel[i], mXPoint+i*mXScale- 15, mYPoint+20, paintText);
            } catch (Exception e) {
            }
        }
        canvas.drawLine(mXPoint+mXLength, mYPoint, mXPoint+mXLength-6, mYPoint-3, paint);//箭头
        canvas.drawLine(mXPoint+mXLength, mYPoint, mXPoint+mXLength-6, mYPoint+3, paint);
        for(int i = 0;i*mXScale<mXLength;i++){
            //数据值（两点确定一条直线）
            if(i>0&&YCoord(mData[i-1])!=-999&&YCoord(mData[i])!=-999)  //保证有效数据
                //画折线
                canvas.drawLine(mXPoint+(i-1)*mXScale, YCoord(mData[i-1]), mXPoint+i*mXScale, YCoord(mData[i]), paint);
            //画转折点上方的数据值
            canvas.drawText(mData[i],mXPoint+i*mXScale,YCoord(mData[i])-15,paintText);

            if(Float.parseFloat(mData[i])<=mRange[0]){
                paintPoint.setColor(Color.parseColor("#FF0000"));
            }else if(Float.parseFloat(mData[i])>=mRange[2]) {
                paintPoint.setColor(Color.parseColor("#FBAD5F"));
            }else{
                paintPoint.setColor(Color.parseColor("#C8E1A8"));
            }
            //设置圆点为实心的
//            paint.setStyle(Paint.Style.FILL);
            //画两线之间的转折点
            canvas.drawCircle(mXPoint+i*mXScale,YCoord(mData[i]), 6, paintPoint);

        }

        //设置标题的内容，位置，字体大小
        paintText.setTextSize(16);
        canvas.drawText(mTitle, 200, 20, paintText);

    }
    private float YCoord(String y0)  //计算绘制时的Y坐标，无数据时返回-999
    {
        float y;
        try
        {
            y=Float.parseFloat(y0);
        }
        catch(Exception e)
        {
            return -999;    //出错则返回-999
        }
        try
        {
            return mYPoint-y*mYScale/Integer.parseInt(mYLabel[1]);
        }
        catch(Exception e)
        {
        }
        return y;
    }

    //根据数据的数量计算横坐标刻度
    private Integer calculateXScale() {
        mXLength = getWidth()-200;
        mXScale = mXLength/(mData.length-1);
        return mXScale;
    }

    private void drawAboutYs(Canvas canvas){
        yCoords = new TreeMap<>();
        yCoords.put(mRange[0], Color.parseColor("#FF0000"));//红色偏低
        yCoords.put(mRange[1], Color.parseColor("#C8E1A8"));//绿色正常
        yCoords.put(mRange[2], Color.parseColor("#FBAD5F"));//橘色偏高

        for (Map.Entry<Float,Integer> entry : yCoords.entrySet()) {

            // y坐标上的线的颜色
            dashedPaint.setColor(entry.getValue());

            Path path = new Path();
            // 画线
            path.reset();
            path.moveTo(getPaddingLeft(), YCoord(entry.getKey()+""));//起始点坐标
            path.lineTo(getWidth(), YCoord(entry.getKey()+""));//终点坐标
            canvas.drawPath(path, dashedPaint);
            canvas.drawText(entry.getKey()+"", getPaddingLeft()+20, YCoord(entry.getKey()+"")-10, paintText);
        }
    }

}
