package com.p2p.bawei.p2pinvest1801.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.framework.base.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.ui.FlowLayout;

import java.util.ArrayList;

public class ProductHotFragment extends BaseFragment {
    private FlowLayout hotFlowLayout;
    private ArrayList<String> names = new ArrayList<>();
    //背景颜色数据源
    private int[] color = {0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    //字体颜色数据源
    private int[] textColor ={Color.parseColor("#080808"),Color.parseColor("#636363"),Color.parseColor("#B03060")};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        //为布局添加内容
        for (int i = 0; i < names.size() ; i++) {
            addTextView(names.get(i));
        }
    }

    @Override
    protected void initView() {
        hotFlowLayout = (FlowLayout) findViewById(R.id.hotFlowLayout);
        setTwoFlowLayout();
    }

    private void setTwoFlowLayout() {
        //添加数据
        names.add("新手福利计划");
        names.add("财神到90天计划");
        names.add("硅谷计划");
        names.add("30天理财计划");
        names.add("180天理财计划");
        names.add("月月升");
        names.add("中情局投资商业经营");
        names.add("屌丝下海经商计划");
        names.add("美人鱼影视拍摄投资");
        names.add("Android培训老师自己周转");
        names.add("养猪场计划");
    }

    private void addTextView(String str) {
        TextView child = new TextView(getContext());
        //随机颜色
        int newColor = color[(int) (color.length * Math.random())];
        int newColorBac = color[(int) (textColor.length * Math.random())];
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(20,20,10,10);
        child.setLayoutParams(params);
        child.setText(str);
        child.setTextSize(30);
        child.setBackgroundResource(R.drawable.flowlayout_textview);
        child.setTextColor(newColorBac);
        initEvents(child);//监听
        hotFlowLayout.addView(child);
    }

    private void initEvents(final TextView child) {
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage(child.getText().toString());
            }
        });
    }
}
