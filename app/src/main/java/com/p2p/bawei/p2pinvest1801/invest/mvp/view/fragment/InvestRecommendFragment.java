package com.p2p.bawei.p2pinvest1801.invest.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.invest.adapter.StellarMapAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部
 */
public class InvestRecommendFragment extends Fragment {

    private StellarMap stellarMap;
    private List<String> list;


    public InvestRecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest_recommend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stellarMap = view.findViewById(R.id.stellar);

        list = new ArrayList<>();
        list.add("新手福利计划");
        list.add("财神道90天计划");
        list.add("月月升理财计划");
        list.add("180天理财计划(加息5%)");
        list.add("中情局投资商业经营");
        list.add("中学老师购买车辆");
        list.add("屌丝下海经商计划");
        list.add("美人鱼影视拍摄投资");
        list.add("android老师自己周转");
        list.add("摩托罗拉洗钱计划");
        list.add("优化网络请求");
        list.add("android大改革");
        list.add("Java的崛起");
        list.add("C++底层");


        stellarMap.setAdapter(new StellarMapAdapter(getContext(), list));

        stellarMap.setInnerPadding(15, 15, 15, 15);

    }
}
