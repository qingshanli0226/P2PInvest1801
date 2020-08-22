package com.p2p.bawei.p2pinvest1801.mvp.view.fra.childFra;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fynn.fluidlayout.FluidLayout;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.Share;
import com.p2p.bawei.p2pinvest1801.bean.AllBean;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class ChlidFragment3 extends Fragment {
    private FluidLayout fluid;
    private int x;
    View view;
    private int[] color = {0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};
    private TagFlowLayout idFlowlayout;
    private List<String>  strlist=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ff3,container,false);

//        fluid = view.findViewById(R.id.fluid);
        idFlowlayout = view.findViewById(R.id.id_flowlayout);


        List<AllBean.ResultBean> alllist = Share.Alllist;
        for(int i = 0;i < alllist.size();i++){
            strlist.add(alllist.get(i).getName());
        }

//        for ( x=0; x < Share.Alllist.size(); x++) {
//            int newcolor = color[(int) (color.length * Math.random())];
//            GradientDrawable drawable=new GradientDrawable();
//            drawable.setShape(GradientDrawable.RECTANGLE);
//            drawable.setGradientType(GradientDrawable.RECTANGLE);
//            drawable.setCornerRadius(60);
//            drawable.setColor(newcolor);
//            TextView tv = new TextView(getContext());
//            tv.setText(Share.Alllist.get(x).getName());
//            tv.setTextSize(16);
//            tv.setBackground(drawable);
//            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.setMargins(3, 3, 3, 3);
//            fluid.addView(tv, params);
//
//        }
        idFlowlayout.setAdapter(new TagAdapter<String>(strlist) {
            @Override
            public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String s) {
            int newcolor = color[(int) (color.length * Math.random())];
            GradientDrawable drawable=new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.RECTANGLE);
            drawable.setCornerRadius(60);
            drawable.setColor(newcolor);
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.ff3_auto2,
                        idFlowlayout, false);
            tv.setText(s);
            tv.setBackground(drawable);
                return tv;
            }
        });

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                Toast.makeText(getActivity(), strlist.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }
}
