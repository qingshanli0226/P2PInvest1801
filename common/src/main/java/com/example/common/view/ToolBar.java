package com.example.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common.R;

public class ToolBar extends RelativeLayout {

    private IToolBarClickListner iToolBarClickListner;

    private ImageView toolBarLeftImg;
    private ImageView toolBarRightImg;
    private TextView toolBarTitle;

    private boolean isShowLeft = true;
    private boolean isShowTitle = true;
    private boolean isShowRight = true;
    private String titleText;
    private int leftImgId;

    public void setiToolBarClickListner(IToolBarClickListner iToolBarClickListner) {
        this.iToolBarClickListner = iToolBarClickListner;
    }

    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        initToolBarAttrs(context,attrs);

        LayoutInflater.from(context).inflate(R.layout.toolbar_layout,this);
        toolBarLeftImg = findViewById(R.id.toolBarLeftImg);
        toolBarRightImg = findViewById(R.id.toolBarRightImg);
        toolBarTitle = findViewById(R.id.toolBarTitle);

        //使用属性值来控制toolbar里控件的显示
        if(!isShowLeft) showNotLeft();
        if(!isShowTitle) showNotTitle();
        if(!isShowRight) showNoRight();
        if (titleText!=null) setToolBarTitle(titleText);
        if (leftImgId!=0) setToolBarLeftImg(leftImgId);

        initListener();
    }

    private void initListener() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iToolBarClickListner != null){
                    iToolBarClickListner.onLeftClick();
                }
            }
        });

        toolBarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iToolBarClickListner != null){
                    iToolBarClickListner.onRightClick();
                }
            }
        });
    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        isShowLeft = typedArray.getBoolean(R.styleable.ToolBar_left_show,true);
        isShowTitle = typedArray.getBoolean(R.styleable.ToolBar_title_show,true);
        isShowRight= typedArray.getBoolean(R.styleable.ToolBar_right_show,true);
        titleText = typedArray.getString(R.styleable.ToolBar_title_text);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_left_src,0);
    }

    //可以修改toolbar的显示的主题
    public void setToolBarTitle(String title) {
        toolBarTitle.setText(title);
    }

    //修改左侧显示图片
    public void setToolBarLeftImg(int imgId) {
        toolBarLeftImg.setImageResource(imgId);
    }

    //左侧图片也不显示
    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }

    //不显示title
    public void showNotTitle() {
        toolBarTitle.setVisibility(GONE);
    }

    //右侧图片不显示
    public void showNoRight(){
        toolBarRightImg.setVisibility(GONE);
    }

    public interface IToolBarClickListner{
        void onLeftClick();
        void onRightClick();

    }

}
