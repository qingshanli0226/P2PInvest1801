package com.bw.common.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bw.common.R;

import java.util.ArrayList;

public class BottomBar extends FrameLayout {
    private IBottomBarSelectListener iBottomBarSelectListener;
    private ArrayList<RadioButton> buttons=new ArrayList<>();
    public static final int HOME_INDEX = 0;
    public static final int TYPE_INDEX = 1;
    public static final int SHOPCAR_INDEX = 2;
    public static final int MINE_INDEX = 3;

    private ImageView homeimg;
    private ImageView typeimg;
    private ImageView shopcarimg;
    private ImageView mineimg;

    private View view;


    private int selectColor=Color.BLUE;

    public BottomBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        view = LayoutInflater.from(context).inflate(R.layout.bottombar_layout, this);

        homeimg = view.findViewById(R.id.home_img);
        typeimg = view.findViewById(R.id.type_img);
        shopcarimg =view.findViewById(R.id.shopcar_img);
        mineimg= view.findViewById(R.id.mine_img);

        initView();

        view.findViewById(R.id.type).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(TYPE_INDEX);
            }
        });

        view.findViewById(R.id.home).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(HOME_INDEX);
            }
        });

        view.findViewById(R.id.shopcar).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(SHOPCAR_INDEX);
            }
        });

        view.findViewById(R.id.mine).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(MINE_INDEX);
            }
        });

        setView(0);

    }

    private void initView() {
        RadioButton homeButton = view.findViewById(R.id.home);
        RadioButton typeButton = view.findViewById(R.id.type);
        RadioButton shopcarButton = view.findViewById(R.id.shopcar);
        RadioButton mineButton = view.findViewById(R.id.mine);
        buttons.clear();
        buttons.add(homeButton);
        buttons.add(typeButton);
        buttons.add(shopcarButton);
        buttons.add(mineButton);
    }

    //数组的长度为4
    public void setTabTitle(String[] tabTitles) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(tabTitles[i]);
        }
    }

    public void setView(int num){
        for (int i = 0; i < buttons.size(); i++) {
            RadioButton radioButton = buttons.get(i);
            if (i==num){
                radioButton.setTextColor(selectColor);
                setImgs();
                switch (num){
                    case HOME_INDEX:
                        homeimg.setImageResource(R.drawable.bottom02);
                        break;
                    case TYPE_INDEX:
                        typeimg.setImageResource(R.drawable.bottom04);
                        break;
                    case SHOPCAR_INDEX:
                        shopcarimg.setImageResource(R.drawable.bottom06);
                        radioButton.setTextColor(Color.RED);
                        break;
                    case MINE_INDEX:
                        mineimg.setImageResource(R.drawable.bottom08);
                        break;
                }

                if (iBottomBarSelectListener!=null) {
                    iBottomBarSelectListener.onBottomBarSelected(num);
                }
            }else {
                radioButton.setTextColor(Color.BLACK);
            }


        }
    }

    private void setImgs(){
        homeimg.setImageResource(R.drawable.bottom01);
        typeimg.setImageResource(R.drawable.bottom03);
        shopcarimg.setImageResource(R.drawable.bottom05);
        mineimg.setImageResource(R.drawable.bottom07);

    }

    public void setBottomBarSelectListener(IBottomBarSelectListener listener) {
        this.iBottomBarSelectListener = listener;
    }

    //定义一个接口，；这个接口，Activity或者Fragment实现这个接口，通过这个接口达到自定义view和Activity或者Fragment之间的通信
    public interface IBottomBarSelectListener {
        void onBottomBarSelected(int selectIndex);
    }

    public void unBottomBarSelectListener() {
        this.iBottomBarSelectListener = null;
    }
}
