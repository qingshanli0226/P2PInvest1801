package com.bw.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.common.R;

public class ToolBar extends LinearLayout {
    private String text;
    private int text_color;
    private float text_size;
    private int leftimg;
    private int rightimg;
    private boolean leftsee;
    private boolean rightsee;
    private OnToolBarClick onToolBarClick;


    public ToolBar(Context context) {
        super(context);
        init(context, null, 0);
    }


    public ToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initAttr(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.toolbar, this);

        ImageView left_img = view.findViewById(R.id.left_img);
        ImageView right_img = view.findViewById(R.id.right_img);
        TextView tool_text = view.findViewById(R.id.tool_text);
        tool_text.setText(text + "");
        tool_text.setTextColor(text_color);
        tool_text.setTextSize(text_size);
        left_img.setImageResource(leftimg);

        if (leftsee) {
            left_img.setVisibility(VISIBLE);
        } else {
            left_img.setVisibility(GONE);
        }

        right_img.setImageResource(rightimg);

        if (rightsee) {
            right_img.setVisibility(VISIBLE);
        } else {
            right_img.setVisibility(GONE);
        }

        left_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onToolBarClick!=null){
                    onToolBarClick.onLeftClick();
                }
            }
        });

        right_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onToolBarClick!=null){
                    onToolBarClick.onRightClick();
                }
            }
        });
    }


    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        text = array.getString(R.styleable.ToolBar_text);
        text_color = array.getColor(R.styleable.ToolBar_toolBar_text_color, Color.BLACK);
        text_size = array.getDimension(R.styleable.ToolBar_text_size, 25);
        leftimg = array.getResourceId(R.styleable.ToolBar_left_img, R.mipmap.ic_launcher_round);
        rightimg = array.getResourceId(R.styleable.ToolBar_right_img, R.mipmap.ic_launcher_round);
        leftsee = array.getBoolean(R.styleable.ToolBar_left_see, false);
        rightsee = array.getBoolean(R.styleable.ToolBar_right_see, false);
    }

    public void setOnToolBarClick(OnToolBarClick onToolBarClick) {
        this.onToolBarClick = onToolBarClick;
    }

    public interface OnToolBarClick {
        void onLeftClick();

        void onRightClick();
    }

    public void unOnToolBarclick() {
        this.onToolBarClick = null;
    }
}
