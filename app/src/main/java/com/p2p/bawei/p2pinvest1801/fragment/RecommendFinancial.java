package com.p2p.bawei.p2pinvest1801.fragment;

import com.bw.framwork.view.BaseFragment;
import com.leon.stellarmap.lib.StellarMap;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.adapter.MyAdapter;

import java.util.ArrayList;

public class RecommendFinancial extends BaseFragment {
    private ArrayList<String> titles=new ArrayList<>();
    private StellarMap stellarMap;
    private MyAdapter adapter;

    @Override
    public void initView() {

        stellarMap = (StellarMap) findViewById(R.id.stellarmap);
        adapter=new MyAdapter(getContext(),titles);
        stellarMap.setAdapter(adapter);


    }

    @Override
    public void initData() {
        titles.clear();
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
        titles.add("Giao");
    }

    @Override
    public int bandLayout() {
        return R.layout.fragment_recommend_lc;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
