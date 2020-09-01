package com.p2p.bawei.p2pinvest1801.invest.mvp.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fynn.fluidlayout.FluidLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.custom.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

/**
 * 全部
 */
public class InvestHotFragment extends Fragment {
    private TagFlowLayout idFlowlayout;


    private String[] strs = new String[]{"今日发表", "李彦宏", "署名文章", "在人工智能", "这场科技", "浪潮中", "中国", "与其他", "国家"
            , "站在同一起跑线上", "中国的科学家", "工程师", "企业家必须全力以赴", "让这一次", "的全球科技创新",
            "尽快迈入", "让我们每个人", "都激动万分的中国时刻"};
    private int[] color = {
            Color.parseColor("#ff0000"),
            Color.parseColor("#CCFF00"),
            Color.parseColor("#FF9966"),
            Color.parseColor("#CC99CC"),
            Color.parseColor("#CCCCCC"),
            Color.parseColor("#CCCC33"),
            Color.parseColor("#666699"),
            Color.parseColor("#FF9966")
    };
    private FluidLayout fluid;

    public InvestHotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_hot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idFlowlayout = view.findViewById(R.id.id_flowlayout);
        flowLayout();

        fluid = view.findViewById(R.id.fluid);
        for (int x = 0; x < strs.length; x++) {

            int newcolor = color[(int) (color.length * Math.random())];
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(60);
            drawable.setColor(newcolor);



            TextView tv = new TextView(getContext());
            tv.setPadding(10, 10, 10, 10);
            tv.setBackground(drawable);
            tv.setText(strs[x]);
            tv.setTextSize(16);

            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            fluid.addView(tv, params);


        }

    }

    private void flowLayout() {
        idFlowlayout.setAdapter(new TagAdapter<String>(strs) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String s) {

                int newcolor = color[(int) (color.length * Math.random())];
                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setGradientType(GradientDrawable.RECTANGLE);
                drawable.setCornerRadius(60);
                drawable.setColor(newcolor);

                TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.item_text,
                        idFlowlayout, false);
                tv.setText(s);
                tv.setPadding(10, 10, 10, 10);
                tv.setBackground(drawable);

                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
            }

        });


        //点击事件
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                Toast.makeText(getActivity(), strs[position], Toast.LENGTH_SHORT).show();
                return true;
            }

        });
        //回调 ????
        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });

//        //预先设置选中
//        idFlowlayout.getAdapter().setSelectedList(1, 3, 5, 7, 8, 9);
//        //获得所有选中的pos集合
//        idFlowlayout.getSelectedList();

    }
}
