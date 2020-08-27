package com.p2p.bawei.p2pinvest1801.mvp.view.fragment;

import com.example.lib_core.mvp.view.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.StellarMapAdapter;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragmentRecommendAFinancialFragment extends BaseFragment{
    private StellarMap stellarMap;
    private List<String> list = new ArrayList<>();
    @Override
    public void initView() {
        for (int i = 0 ; i<20;i++){
            list.add("喵喵"+i+"号");
        }
        stellarMap = (StellarMap) findViewById(R.id.stellarMap);
        StellarMapAdapter stellarMapAdapter = new StellarMapAdapter(list, getContext());
        stellarMap.setAdapter(stellarMapAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.main_investment_recommend_a_financial_fragment;
    }


}
