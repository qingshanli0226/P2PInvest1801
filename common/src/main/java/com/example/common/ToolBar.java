package com.example.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToolBar extends RelativeLayout {
    private ClicksListener clicksListener;
    public ToolBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    ImageView imageView;
    TextView textView;
    private ImageView toolbarRight;
    boolean left_show = true;
    boolean right_show = true;
    private String titleText;
    private void init(Context context,AttributeSet attributeSet,int defStyleAttr){
        initToolBarAttrs(context,attributeSet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.toolbar, this);
        imageView = inflate.findViewById(R.id.toolbar_left);
        textView = inflate.findViewById(R.id.toolbar_text);
        toolbarRight =inflate.findViewById(R.id.toolbar_right);
        if(left_show)showLeft();
        if(titleText!=null)setText(titleText);
        if(right_show)showRight();
        initListener();
    }

    private void initToolBarAttrs(Context context, AttributeSet attributeSet) {
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.ToolBar);
        titleText = array.getString(R.styleable.ToolBar_center_text);
        left_show = array.getBoolean(R.styleable.ToolBar_left_show, false);
        right_show = array.getBoolean(R.styleable.ToolBar_right_show,false);
    }

    private void initListener() {
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicksListener != null){
                    clicksListener.leftclick();
                }
            }
        });
        toolbarRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicksListener != null){
                    clicksListener.rightclick();
                }
            }
        });
    }

    public void showLeft(){
        imageView.setVisibility(VISIBLE);
    }
    public void showRight(){
        toolbarRight.setVisibility(GONE);
    }
    public void setText(String text){
        textView.setText(text);
    }

    public void setClicksListener(ClicksListener clicksListener){
        this.clicksListener = clicksListener;
    }

    public interface ClicksListener{
        void leftclick();
        void rightclick();
    }
}
