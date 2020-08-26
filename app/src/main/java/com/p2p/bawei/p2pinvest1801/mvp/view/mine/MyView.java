package com.p2p.bawei.p2pinvest1801.mvp.view.mine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.p2p.bawei.p2pinvest1801.bean.MyViewBean;

import java.util.ArrayList;

public class MyView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder surfaceHolder;
    ArrayList<MyViewBean> list = new ArrayList<>();
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new MyThread().start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    boolean flag = false;
    public boolean setflag(){
        return flag;
    }
    public void addBean(String text, int color, int size, float textx, float texty, int count){
        list.add(new MyViewBean(color, text, textx, texty+count,size));
    }
    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            Paint paint = new Paint();

            paint.setAntiAlias(true);//抗锯齿
            while (true){
                Canvas canvas = surfaceHolder.lockCanvas();
                if(canvas == null){
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    int color = list.get(i).getColor();
                    String title = list.get(i).getTitle();
                    float x = list.get(i).getX();
                    float y = list.get(i).getY();
                    int textsize = list.get(i).getTextsize();
                    paint.setTextSize(textsize);
                    paint.setColor(color);
                    canvas.drawText(title,x, y, paint);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

        }
    }
}
