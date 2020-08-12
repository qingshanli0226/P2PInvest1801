package com.p2p.bawei.p2pinvest1801.fragment;

import com.example.framework.base.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    private ArrayList<String> stringArrayList;

    public HomeFragment(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
