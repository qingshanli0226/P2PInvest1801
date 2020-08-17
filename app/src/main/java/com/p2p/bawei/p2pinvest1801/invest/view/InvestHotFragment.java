package com.p2p.bawei.p2pinvest1801.invest.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.view.MyGroup;

import java.util.ArrayList;
import java.util.List;

public class InvestHotFragment extends BaseFragment {
    private MyGroup mLiu;
    List<String> list = new ArrayList<>();
    private int[] color = {0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
        mLiu = (MyGroup) findViewById(R.id.liu);
        list = new ArrayList<>();
        list.add("新手福利计划");
        list.add("财神道90天计划");
        list.add("铁路局汇款计划");
        list.add("屌丝迎娶白富美计划");
        list.add("硅谷计划");
        list.add("30天理财计划");
        list.add("180天理财计划");
        list.add("月月升");
        list.add("中情局投资商业经营");
        list.add("大学老师购买车辆");
        list.add("JAVA天下第一");
        list.add("美人鱼影视拍摄投资");
        list.add("旅游公司扩大规模");
        list.add("上班摸鱼");
        list.add("摩托罗拉洗钱计划");


//往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        if (mLiu != null) {
            mLiu.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {

            //动态创建资源文件，背景颜色
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(60);

            int nowcolor = color[(int) (color.length * Math.random())];
            drawable.setColor(nowcolor);


            TextView tv = new TextView(getContext());
            tv.setPadding(28, 10, 28, 10);
            tv.setText(list.get(i));
            tv.setMaxEms(10);
            tv.setTextSize(25);
            tv.setSingleLine();
            tv.setLayoutParams(layoutParams);
            tv.setBackground(drawable);
            mLiu.addView(tv, layoutParams);
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), list.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int bandLayout() {
        return R.layout.hot_liu_layout;
    }
}
