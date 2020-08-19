package com.p2p.bawei.p2pinvest1801.home.invest;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.net.activity_bean.InvestBean;
import com.fynn.fluidlayout.FluidLayout;
import com.p2p.bawei.p2pinvest1801.CacheManager;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    private FluidLayout hot_fluid;

    private int x;
//    private FlowLayout hot_flow;

    private int[] color = {0xFFFF34B3, 0xFF9ACD32, 0xFF9400D3, 0xFFEE9A00, 0xFF9C54FF, 0xFF3B78};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        hot_fluid = view.findViewById(R.id.hot_fluid);
        //        hot_flow=view.findViewById(R.id.hot_flow);
//        initData();
        Random random = new Random();

        List<InvestBean.ResultBean> result = CacheManager.getInstance().getInvestBean().getResult();
        for (x = 0; x < result.size(); x++) {
            int newcolor = color[(int) (color.length * Math.random())];
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setGradientType(GradientDrawable.RECTANGLE);
            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            drawable.setCornerRadius(60);
            drawable.setColor(Color.rgb(red,green,blue));

            TextView tv = new TextView(getContext());
            tv.setText(result.get(x).getName());
            tv.setTextSize(16);
            tv.setBackground(drawable);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(3, 3, 3, 3);
            hot_fluid.addView(tv, params);



        }

//    private void initData() {
//        Random random = new Random();
//        List<InvestBean.ResultBean> result = CacheManager.getInstance().getInvestBean().getResult();
//        for (int i = 0; i < result.size(); i++) {
//            String name = result.get(i).getName();
//            TextView textView = new TextView(getActivity());
//            textView.setText(name);
//            int red = random.nextInt(211);
//            int green = random.nextInt(211);
//            int blue = random.nextInt(211);
//            textView.setBackgroundColor(Color.rgb(red, green, blue));
//            hot_fluid.addView(textView);
//        }
//
//    }
        return view;
    }
}
